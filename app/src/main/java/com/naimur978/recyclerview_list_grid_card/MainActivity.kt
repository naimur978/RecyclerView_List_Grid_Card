package com.naimur978.recyclerview_list_grid_card

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naimur978.recyclerview_list_grid_card.adapter.CardViewAdapter
import com.naimur978.recyclerview_list_grid_card.adapter.GridHeroAdapter
import com.naimur978.recyclerview_list_grid_card.adapter.ListHeroAdapter
import com.naimur978.recyclerview_list_grid_card.data.HeroesData
import com.naimur978.recyclerview_list_grid_card.model.HeroModel

class MainActivity : AppCompatActivity() {

    private lateinit var rvHeroes:RecyclerView
    private var list:ArrayList<HeroModel> = arrayListOf()
    private var title:String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)

        list.addAll(HeroesData.listData)
        setActionBar(title)
        showRecyclerList()

    }

    private fun setActionBar(title:String){
        supportActionBar?.title = title
    }

    private fun showSelectedHero(hero:HeroModel){
        Toast.makeText(this,"You Selected ${hero.name}",Toast.LENGTH_SHORT).show()
    }

    private fun showRecyclerList(){
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickedCallBack(object : ListHeroAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: HeroModel) {
                showSelectedHero(data)
            }
        })
    }
    private fun showRecyclerGrid(){
        rvHeroes.layoutManager = GridLayoutManager(this,2)
        val gridHeroAdapter = GridHeroAdapter(list)
        rvHeroes.adapter = gridHeroAdapter

        gridHeroAdapter.setOnClickCallBack(object: GridHeroAdapter.OnItemClickedCallback{
            override fun onItemClicked(data: HeroModel) {
                showSelectedHero(data)
            }
        })

    }
    private fun showRecyclerCardView(){
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val cardViewAdapter = CardViewAdapter(list)
        rvHeroes.adapter = cardViewAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode:Int){
        when(selectedMode){
            R.id.action_list ->{
                title = "Mode List"
                showRecyclerList()
            }
            R.id.action_grid ->{
                title = "Mode Grid"
                showRecyclerGrid()
            }
            R.id.action_card ->{
                title = "Mode CardView"
                showRecyclerCardView()
            }
        }
        setActionBar(title)
    }

}