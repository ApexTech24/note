package com.technote.fliknote.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.technote.fliknote.R
import com.technote.fliknote.adapter.NoteAdapter
import com.technote.fliknote.databinding.FragmentHomeBinding
import com.technote.fliknote.model.Note
import com.technote.fliknote.viewModel.NoteViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val noteViewModel: NoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater,container,false)

        binding.addNoteFab.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addNewNoteFragment)
        }

        val noteAdapter=NoteAdapter(::onMenuItemClicked, ::onItemClicked)
        binding.noteRecyclerView.layoutManager= LinearLayoutManager(requireActivity())
        binding.noteRecyclerView.adapter=noteAdapter
        noteViewModel.getAllNote().observe(viewLifecycleOwner){
            noteAdapter.submitList(it)
        }
        return binding.root
    }

    private fun onMenuItemClicked(note: Note){
        noteViewModel.deleteNote(note)
    }

    private fun onItemClicked(note: Note){
        val bundle= bundleOf("id" to note.id)
        findNavController().navigate(R.id.action_homeFragment_to_addNewNoteFragment, bundle)
    }
}