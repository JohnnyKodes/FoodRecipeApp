package com.example.recipeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_user_recipes.view.*

class UserRecipesFragment : Fragment() {
    private lateinit var recipeViewModel: RecipeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_recipes, container, false)

        val query = FirebaseFirestore.getInstance().collection("recipes").orderBy("name")
        val options = FirestoreRecyclerOptions.Builder<Recipe>().setQuery(query, Recipe::class.java).build()
        val adapter = RecipesListAdapter(options)
        val recyclerView = view.recyclerViewUser
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.startListening()

        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)


        return view
    }

}