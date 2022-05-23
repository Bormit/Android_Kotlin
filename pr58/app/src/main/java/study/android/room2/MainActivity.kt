package study.android.room2


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var rbStudent: RadioButton
    private lateinit var rbSubject: RadioButton
    private lateinit var spinner: Spinner
    private lateinit var listCaption: TextView
    private lateinit var recyclerView: RecyclerView

    val db by lazy {
        Room.databaseBuilder(
            this,
            SchoolDatabase::class.java, "school.db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rbStudent = findViewById(R.id.rbStudent)
        rbSubject = findViewById(R.id.rbSubject)
        spinner = findViewById(R.id.spinner)
        listCaption = findViewById(R.id.listCaption)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        var items1:MutableList<String> =  arrayListOf()
        var items2:MutableList<String> =  arrayListOf()
        var isStudent:Boolean = false
        var isSubject:Boolean = false

        val change1 = {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, items1
            )

            spinner.adapter = adapter
        }

        val change2 = {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, items2
            )

            spinner.adapter = adapter
        }

        rbStudent.setOnClickListener{
            listCaption.text = "Student's subjects"
            // так же должен меняться выпадающий список
            val dao = db.schoolDao
            lifecycleScope.launch {
                items1.clear()
                val res = dao.getStudent()
                for(element in res){
                    items1.add(element.student.studentName)
                }
                isStudent=true
                isSubject=false
                change1.invoke()
            }

        }

        rbSubject.setOnClickListener{
            listCaption.text = "Students study"
            // также должен меняться выпадающий список
            val dao = db.schoolDao
            lifecycleScope.launch {
                items2.clear()
                val res = dao.getSubject()
                for(element in res){
                    items2.add(element.subject.subjectName)
                }

                isSubject=true
                isStudent=false
                change2.invoke()
            }
        }

//        val items = arrayOf("Здесь", "должен", "располагаться", "список", "учеников", "или предметов")




//        val adapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_spinner_item, items
//        )

//        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?,
                position: Int, id: Long
            ) {
                val dao = db.schoolDao
                if(isStudent){
                    lifecycleScope.launch {
                        val res = dao.getSubjectsOfStudent(items1[id.toInt()])
                        var fin:MutableList<String> =  arrayListOf()
                        for(element in res){
                            for((k) in element.subjects.withIndex()) {
                                fin.add(element.subjects[k].subjectName)
                            }
                        }

                        val adapter = CustomAdapter(fin)
                        recyclerView.adapter = adapter
                    }
                }

                if(isSubject){
                    lifecycleScope.launch {
                        val res = dao.getStudentsOfSubject(items2[id.toInt()])
                        var fin:MutableList<String> =  arrayListOf()
                        for(element in res){
                            for((k) in element.students.withIndex()) {
                                fin.add(element.students[k].studentName)
                            }
                        }

                        val adapter = CustomAdapter(fin)
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        }


        val dao = db.schoolDao;

        lifecycleScope.launch {
            DataExample.directors.forEach { dao.insertDirector(it) }
            DataExample.schools.forEach { dao.insertSchool(it) }
            DataExample.subjects.forEach { dao.insertSubject(it) }
            DataExample.students.forEach { dao.insertStudent(it) }
            DataExample.studentSubjectRelations.forEach { dao.insertStudentSubjectCrossRef(it) }
        }
    }

}