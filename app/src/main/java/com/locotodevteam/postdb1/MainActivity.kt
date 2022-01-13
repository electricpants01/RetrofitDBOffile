package com.locotodevteam.postdb1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.locotodevteam.postdb1.adapter.PostAdapter
import com.locotodevteam.postdb1.databinding.ActivityMainBinding
import com.locotodevteam.postdb1.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var recycler: RecyclerView
    val viewmodel: MainActivityViewModel by viewModels()
    lateinit var room: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        room = Room.databaseBuilder(this, AppDatabase::class.java, "firstPostDB").build()
        // init recyclerview

        initRecycler()
        initButtonListeners()
        initSubscription()
    }

    private fun initButtonListeners() {
        binding.button.setOnClickListener {
            val text = binding.txtFieldId.text.toString()
            viewmodel.getPostById(text.toInt())
        }
    }

    private fun initSubscription() {
        viewmodel.myPosts.observe(this, {
            CoroutineScope(Dispatchers.IO).launch { room.getPostDao().insert(it) }
        })
        room.getPostDao().getAll().observe(this,{
            recycler.adapter = PostAdapter(it)
        })

    }

    private fun initRecycler(){
        recycler = binding.recyclerView
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = PostAdapter(emptyList())
    }
}