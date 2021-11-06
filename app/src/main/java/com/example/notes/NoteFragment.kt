package com.example.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.notes.RecyclerViewFragment.Companion.newNote

private const val LABEL_FROM = "labelFrom"

class NoteFragment:Fragment() {


    companion object {
        fun newInstance(from: String) = NoteFragment().apply {
            arguments = bundleOf(LABEL_FROM to from)
        }
    }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_note, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            view.run {
                val myadapter=MyAdapter(objects)
                val title=findViewById<EditText>(R.id.titleText)
                val description=findViewById<EditText>(R.id.descriptionText)
                val saveBtn:Button=findViewById(R.id.saveBtn)
                if(save) {
                    saveBtn.visibility = View.VISIBLE
                }
                saveBtn.setOnClickListener {
                    if(save && (title.text.isNotEmpty() && description.text.isNotEmpty())){
                        newNote=NoteElement(title.text.toString(), description.text.toString())
                        objects.add(newNote)
                        myadapter.notifyDataSetChanged()
                        save=false
                        parentFragmentManager.popBackStack()
                    }
                    else
                    {
                        Toast.makeText(context,"Fill in all fields",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
