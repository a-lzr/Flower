package by.tms.flower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

const val REQUEST_CODE_FLOWER_NEW = 0
const val REQUEST_FIELD_FLOWER_NAME = "flower name"
const val REQUEST_FIELD_FLOWER_PRICE = "flower price"
const val REQUEST_FIELD_FLOWER_URL = "flower url"

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var fragmentTop: FlowerFragment? = null
    private var fragmentBottom: FlowerFragment? = null
    private var model: FlowerModel? = null

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addTopFragment.setOnClickListener(this)
        addBottomFragment.setOnClickListener(this)
        removeTopFragment.setOnClickListener(this)
        removeBottomFragment.setOnClickListener(this)
        addFlower.setOnClickListener(this)
        topFragment.setOnClickListener(this)
        bottomFragment.setOnClickListener(this)

        model = ViewModelProvider(this).get(FlowerModel::class.java)
        with(model!!) {
            if (getCount() == 0) {
                addFlower(
                    "rose",
                    1.0,
                    "https://flower-trade.ru/wp-content/uploads/2019/10/Red-Paris555.jpg"
                )
                addFlower(
                    "tulip",
                    2.0,
                    "https://donpion.ua/static/media/uploads/product/One_flower/tulips/.thumbnails/tulips-orange.jpg/tulips-orange-0x700.jpg"
                )
            }
            if (addTop && getCount() > 0)
                setTopFragment()
            if (addBottom && getCount() > 1)
                setBottomFragment()
        }
        updateCount()
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK)
            return
        if (requestCode == REQUEST_CODE_FLOWER_NEW) {
            ViewModelProvider(this).get(FlowerModel::class.java).addFlower(
                name = data?.getStringExtra(REQUEST_FIELD_FLOWER_NAME).toString(),
                price = data?.getDoubleExtra(REQUEST_FIELD_FLOWER_PRICE, 0.0) ?: 0.0,
                url = data?.getStringExtra(REQUEST_FIELD_FLOWER_URL).toString()
            )
            updateCount()
        }
    }

    @Override
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.addTopFragment -> {
                if (model!!.getCount() > 0)
                    setTopFragment()
                else
                    Toast.makeText(this, R.string.add_fragment_failed, Toast.LENGTH_SHORT).show()
            }
            R.id.addBottomFragment -> {
                if (model!!.getCount() > 1)
                    setBottomFragment()
                else
                    Toast.makeText(this, R.string.add_fragment_failed, Toast.LENGTH_SHORT).show()
            }
            R.id.removeTopFragment -> {
                if (fragmentTop != null) {
                    supportFragmentManager.beginTransaction()
                        .remove(fragmentTop!!)
                        .commit()
                    fragmentTop = null
                    model!!.addTop = false
                } else
                    Toast.makeText(this, R.string.delete_fragment_failed, Toast.LENGTH_SHORT).show()
            }
            R.id.removeBottomFragment -> {
                if (fragmentBottom != null) {
                    supportFragmentManager.beginTransaction()
                        .remove(fragmentBottom!!)
                        .commit()
                    fragmentBottom = null
                    model!!.addBottom = false
                } else
                    Toast.makeText(this, R.string.delete_fragment_failed, Toast.LENGTH_SHORT).show()
            }
            R.id.addFlower -> {
                val intent = Intent(this, AddFlower::class.java)
                startActivityForResult(intent, REQUEST_CODE_FLOWER_NEW)
            }
            R.id.topFragment -> {
                showInfo(0)
            }
            R.id.bottomFragment -> {
                showInfo(1)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateCount() {
        count.text = "${getString(R.string.count)}${model!!.getCount()}"
    }

    private fun setTopFragment() {
        fragmentTop = FlowerFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.topFragment, fragmentTop!!)
            .commit()
        fragmentTop!!.updateData(model!!.getFlower(0))
        model!!.addTop = true
    }

    private fun setBottomFragment() {
        fragmentBottom = FlowerFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.bottomFragment, fragmentBottom!!)
            .commit()
        fragmentBottom!!.updateData(model!!.getFlower(1))
        model!!.addBottom = true
    }

    private fun showInfo(index: Int) {
        val intent = Intent(this, InfoActivity::class.java).apply{
            putExtra(REQUEST_FIELD_FLOWER_NAME, model!!.getFlower(index).name)
            putExtra(REQUEST_FIELD_FLOWER_PRICE, model!!.getFlower(index).price)
            putExtra(REQUEST_FIELD_FLOWER_URL, model!!.getFlower(index).url)
        }
        startActivity(intent)
    }
}
