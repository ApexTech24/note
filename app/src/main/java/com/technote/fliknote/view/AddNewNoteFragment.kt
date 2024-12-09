package com.technote.fliknote.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.technote.fliknote.R
import com.technote.fliknote.databinding.FragmentAddNewNoteBinding
import com.technote.fliknote.model.Note
import com.technote.fliknote.viewModel.NoteViewModel


class AddNewNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddNewNoteBinding
    private val noteViewModel: NoteViewModel by activityViewModels()

    private var dateInMillis = ""
    private var timeInMillis = ""
    private var id:Long? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddNewNoteBinding.inflate(inflater,container,false)

        id=arguments?.getLong("id")
        if (id !=null){
            binding.saveBTN.setText("Update")
            noteViewModel.getNoteById(id!!).observe(viewLifecycleOwner){
                binding.titleTIE.setText(it.title)
                binding.descriptionTIE.setText(it.description)
                dateInMillis = it.date
                //binding.dateTV.setText(dateInMillis)
                timeInMillis = it.time
               // binding.timeTV.setText(timeInMillis)
            }
        }
        clickListener()
        return binding.root
    }

    private fun clickListener() {
        binding.saveBTN.setOnClickListener {

            val newTask = Note(
                id = 0,
                title = binding.titleTIE.text.toString().trim(),
                description = binding.descriptionTIE.text.toString().trim(),
                date = dateInMillis,
                time = timeInMillis,
            )

            if (id !=null){
                newTask.id =id!!
                noteViewModel.updateNote(newTask)
            }else{
                noteViewModel.insertNote(newTask)
            }

            Toast.makeText(context, "Task added successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addNewNoteFragment_to_homeFragment)
        }
    }
}