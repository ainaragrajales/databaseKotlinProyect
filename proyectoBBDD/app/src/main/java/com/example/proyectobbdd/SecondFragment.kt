package com.example.proyectobbdd

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectobbdd.databinding.FragmentSecondBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SecondFragment : Fragment() {

    lateinit var miRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView= inflater.inflate(R.layout.fragment_second, container, false)

        var listaLibros:MutableList<Libro> = mutableListOf()
        (activity as MainActivity).miViewModel.listaLibros.observe(activity as MainActivity) { libros->
            libros?.let{listaLibros=it}
            miRecyclerView=rootView.findViewById(R.id.frag2_recyclerView)
            miRecyclerView.layoutManager= LinearLayoutManager(activity)
            miRecyclerView.adapter=Adaptador(listaLibros, activity as MainActivity)
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        activity?.setTitle("Lista libros")

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.modificar)?.isVisible=false
        menu.findItem(R.id.borrar)?.isVisible=false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.guardar->findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
        }
        return super.onOptionsItemSelected(item)
    }


}