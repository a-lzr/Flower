package by.tms.flower

import androidx.lifecycle.ViewModel

data class Flower (var name: String, var price: Double, var url: String)

class FlowerModel: ViewModel() {
    private val list = ArrayList<Flower>()
    var addTop: Boolean = false
    var addBottom: Boolean = false

    fun addFlower (name: String, price: Double, url: String) {
        list.add(Flower(name, price, url))
    }

    fun getFlower (index: Int) : Flower {
        return list[index]
    }

    fun getCount() : Int {
        return list.size
    }
}