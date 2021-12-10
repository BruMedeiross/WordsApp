package com.example.wordsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentWordListBinding


/**
 * A simple [Fragment] subclass.
 * Use the [WordListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WordListFragment : Fragment() {

    //anulável? Porque não é possível inflar o layout até que a onCreateView() seja chamada.
    private var _binding: FragmentWordListBinding? = null

    //variavel somente de acesso _
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var letterId: String

    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        //quando a navegação é realizada usando o nav_graph e argumentos seguros, não há intents.
        //Aqui recupere a LETRA dos argumentos do Fragment
        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }




    }

    // no fragment o layout é inflado no método onCreateView()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = binding.recyclerViewDetail

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        /**setando no wordadapter a letra correspondente ao click*/
        recyclerView.adapter = WordAdapter(letterId, requireContext())

        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )



    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}