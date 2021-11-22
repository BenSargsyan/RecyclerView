package com.example.notes

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar


var save:Boolean=false
var objects= mutableListOf<NoteElement> (
    NoteElement("//////",///////////"),
    NoteElement("//////","///////"))
class RecyclerViewFragment: Fragment() {
    lateinit var notesList: RecyclerView
    lateinit var addNoteBtn: Button
    lateinit var saveBtn: Button
    lateinit var myadapter: MyAdapter
    companion object{
        var newNote:NoteElement = NoteElement("","")
    }


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all_notes) {
            objects.clear()
            myadapter.notifyDataSetChanged()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.run {
            myadapter=MyAdapter(objects)
            super.onViewCreated(view, savedInstanceState)
            notesList = findViewById<RecyclerView>(R.id.noteList)
            addNoteBtn=findViewById<Button>(R.id.addNewNote)
            notesList.adapter = myadapter
            notesList.layoutManager=LinearLayoutManager(context)
            addNoteBtn.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, NoteFragment.newInstance("RecyclerViewFragment"))
                    .addToBackStack(null).commit()
                save = true
            }
            var touch=ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return true
                }
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    objects.removeAt(viewHolder.adapterPosition)
                    myadapter.notifyItemRemoved(viewHolder.adapterPosition)
                }
            })
            touch.attachToRecyclerView(notesList)

        }
    }

}
