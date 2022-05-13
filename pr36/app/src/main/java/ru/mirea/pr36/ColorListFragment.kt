package ru.mirea.pr36

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.content.PackageManagerCompat.LOG_TAG

import android.widget.AdapterView.OnItemClickListener
import androidx.core.content.PackageManagerCompat
import androidx.fragment.app.FragmentManager






class ColorListFragment : Fragment() {
    private var pos: Int = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_color_list, container, false) //получаем разметку color list
        val listView = view.findViewById<ListView>(R.id.listView) // получаем элемент ListView из view
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, getColorNames()) // создаем адаптер,
        // специальный компонент, который связывает источник данных с виджетом списка.
        // Представляет собой простейший адаптер, который связывает массив данных с набором элементов TextView,
        // из которых, к примеру, может состоять ListView
        listView.adapter = adapter //устанавливаем для списка адаптер
        listView.setOnItemClickListener { parent, view, position, id ->
            pos = position
            view.setBackgroundColor(getColorValues().get(position)) //получаем позицию цвета и устанавливаем
            fragmentManager?.beginTransaction()?.replace(R.id.fragment2, instOf())?.commit() //заменяем fragment2 и новый фрагмент
        }
//OnItemClickListener – обрабатывает нажатие на пункт списка
//
//Предоставляет нам метод onItemClick(AdapterView<?> parent, View view, int position, long id), где
//
//parent – View-родитель для нажатого пункта, в нашем случае - ListView
//view – это нажатый пункт, в нашем случае – TextView из android.R.layout.simple_list_item_1
//position – порядковый номер пункта в списке
//id – идентификатор элемента,
//
//Мы в лог будем выводить id и position для элемента, на который нажал
        return view
    }
        private fun instOf(): ColoredFragment { //передача параметров во фрагмент ColoredFragment
        val argument = Bundle()
        argument.putInt("index", pos)
        val fragment = ColoredFragment()
        fragment.arguments = argument
        return fragment
    }

    private fun getColorNames() = resources.getStringArray(R.array.colorNames)
    private fun getColorValues() = resources.getIntArray(R.array.colorValues)


    companion object {
        @JvmStatic
        fun newInstance() =
            ColorListFragment().apply {}
    }
}

//https://startandroid.ru/ru/uroki/vse-uroki-spiskom/85-urok-44-sobytija-v-listview.html
//https://metanit.com/java/android/5.1.php
//https://youtu.be/T2q92injIEw
//https://youtu.be/ETDEKTD3nzs