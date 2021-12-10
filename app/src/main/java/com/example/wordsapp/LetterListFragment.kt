package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding

/**
 *você encontrará nomes de propriedades precedidos por um sublinhado. EX: _BINDING
 * Geralmente, isso significa que a propriedade não pode ser acessada diretamente.
 * Nesse caso, você acessa a vinculação da visualização no LetterListFragment com a propriedade binding.
 * No entanto, a propriedade _binding não precisa ser acessada fora do LetterListFragment.*/
private var _binding: FragmentLetterListBinding? = null
private val binding get() = _binding!!

private lateinit var recyclerView: RecyclerView
private var isLinearLayoutManager = true
/**
 * A simple [Fragment] subclass.
 * Use the [LetterListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LetterListFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    // no fragment o layout é inflado no método onCreateView()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerViewMain
        chooseLayout()

        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )

    }

   //redefina a propriedade _binding para null, já que a visualização não existe mais.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Embora a classe Activity tenha uma propriedade global chamada menuInflater, o fragmento não tem.
    // O inflador do menu é transmitido para onCreateOptionsMenu().
    // Observe também que o método onCreateOptionsMenu() usado em fragmentos não exige uma instrução de retorno. - vide main activity
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    //algumas mudancas com relação a main activity
    //A única diferença importante é que, ao contrário das atividades,
    // um fragmento não é um Context. Não é possível transmiti-lo usando this
    private fun chooseLayout() {
        when (isLinearLayoutManager) {
            true -> {
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = LetterAdapter()
            }
            false -> {
                recyclerView.layoutManager = GridLayoutManager(context, 4)
                recyclerView.adapter = LetterAdapter()
            }
        }
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }



}