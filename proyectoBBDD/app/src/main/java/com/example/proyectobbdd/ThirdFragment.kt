package com.example.proyectobbdd

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController


class ThirdFragment : Fragment() {
    var posicion:Int=0
    lateinit var etTitulo: EditText
    lateinit var etAutor: EditText
    lateinit var etGenero: EditText
    lateinit var etFPublicacion: EditText
    lateinit var miLibro: Libro
    lateinit var etImagen: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        posicion=arguments?.getInt("id") ?:-1
        return inflater.inflate(R.layout.fragment_third, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        val bInsertar=view.findViewById<Button>(R.id.frag3_bInsertar)
        val bBorrar=view.findViewById<Button>(R.id.frag3_bBorrar)
        val bModificar=view.findViewById<Button>(R.id.frag3_bModificar)
        etTitulo=view.findViewById<EditText>(R.id.frag3_etTitulo)
        etAutor=view.findViewById<EditText>(R.id.frag3_etAutor)
        etGenero=view.findViewById<EditText>(R.id.frag3_etGenero)
        etFPublicacion=view.findViewById<EditText>(R.id.frag3_etFecha)
        etImagen=view.findViewById(R.id.frag3_etImagen)
        val tvId=view.findViewById<TextView>(R.id.frag3_tvId)


        if(posicion==-1){
            bBorrar.isEnabled=false
            bModificar.isEnabled=false
            bInsertar.isEnabled=true
            activity?.setTitle("Insertar libros")
        }
        else{
            bBorrar.isEnabled=true
            bModificar.isEnabled=true
            bInsertar.isEnabled=false
            activity?.setTitle("Modificar libros")
            (activity as MainActivity).miViewModel.buscarPorId(posicion)
            (activity as MainActivity).miViewModel.miLibro.observe(activity as MainActivity){libro->
                libro?.let {
                    miLibro = it
                    tvId.text = String.format("ID: $posicion")
                    etTitulo.setText(libro.titulo)
                    etAutor.setText(libro.autor)
                    etGenero.setText(libro.genero)
                    etFPublicacion.setText(libro.fechaPublicacion)
                    etImagen.setText(libro.urlImagen)
                }
            }
        }

        bInsertar.setOnClickListener {
            if(etTitulo.text.isEmpty() || etAutor.text.isEmpty() ||etGenero.text.isEmpty() || etFPublicacion.text.isEmpty() || etImagen.text.isEmpty())
                Toast.makeText(activity,"Tienes que insertar todos los datos", Toast.LENGTH_SHORT).show()
            else{
                (activity as MainActivity).miViewModel.insertar(Libro(titulo=etTitulo.text.toString(),autor =  etAutor.text.toString(),genero =  etGenero.text.toString(),fechaPublicacion =  etFPublicacion.text.toString(), urlImagen = etImagen.text.toString()))
                findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
            }
        }

        bBorrar.setOnClickListener {
            (activity as MainActivity).miViewModel.borrarPorId(posicion)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        bModificar.setOnClickListener {
            if(miLibro.titulo==etTitulo.text.toString() && miLibro.autor==etAutor.text.toString() && miLibro.genero==etGenero.text.toString()&& miLibro.fechaPublicacion==etFPublicacion.text.toString() && miLibro.urlImagen==etImagen.text.toString()){
                Toast.makeText(activity,"No has modificado nada", Toast.LENGTH_SHORT).show()
            }
            else{
                (activity as MainActivity).miViewModel.actualizar(Libro(posicion, etTitulo.text.toString(),etAutor.text.toString(),etGenero.text.toString(),etFPublicacion.text.toString(),etImagen.text.toString()))
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        if (posicion==-1){
            menu.findItem(R.id.modificar)?.isVisible=false
            menu.findItem(R.id.borrar)?.isVisible=false
        }
        else{
            menu.findItem(R.id.guardar)?.isVisible=false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.guardar->{
                if(etTitulo.text.isEmpty() || etAutor.text.isEmpty() || etGenero.text.isEmpty()||etFPublicacion.text.isEmpty()|| etImagen.text.isEmpty())
                    Toast.makeText(activity,"Tienes que insertar todos los datos", Toast.LENGTH_SHORT).show()
                else{
                    (activity as MainActivity).miViewModel.insertar(Libro(titulo= etTitulo.text.toString(),autor =  etAutor.text.toString(),genero =  etGenero.text.toString(),fechaPublicacion =  etFPublicacion.text.toString(), urlImagen = etImagen.text.toString()))
                    findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
                }
            }
            R.id.modificar->{
                if(miLibro.titulo==etTitulo.text.toString() && miLibro.autor == etAutor.text.toString() && miLibro.genero == etGenero.text.toString()&& miLibro.fechaPublicacion == etFPublicacion.text.toString() && miLibro.urlImagen==etImagen.text.toString()){
                    Toast.makeText(activity,"No has modificado nada", Toast.LENGTH_SHORT).show()
                }
                else{
                    (activity as MainActivity).miViewModel.actualizar(Libro(posicion, etTitulo.text.toString(),etAutor.text.toString(),etGenero.text.toString(),etFPublicacion.text.toString(),etImagen.text.toString()))
                    findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
                }
            }
            R.id.borrar->{
                (activity as MainActivity).miViewModel.borrarPorId(posicion)
                findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}