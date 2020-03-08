package by.tms.flower

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_flower.*

class AddFlower : AppCompatActivity(), View.OnClickListener {

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_flower)
        addFlowerToList.setOnClickListener(this)
    }

    @Override
    override fun onClick(view: View?) {
        when (view?.id) {
            addFlowerToList.id -> {
                if (editFlowerName.text.isNotEmpty() && editFlowerPrice.text.isNotEmpty()) {
                    val intent = Intent()
                    intent.putExtra(
                        REQUEST_FIELD_FLOWER_NAME,
                        editFlowerName.text.toString()
                    )
                    intent.putExtra(
                        REQUEST_FIELD_FLOWER_PRICE,
                        editFlowerPrice.text.toString().toDouble()
                    )
                    intent.putExtra(
                        REQUEST_FIELD_FLOWER_URL,
                        editFlowerUrl.text.toString()
                    )
                    setResult(Activity.RESULT_OK, intent)
                    Toast.makeText(this, R.string.add_complete, Toast.LENGTH_SHORT).show()
                    onBackPressed()
                } else
                    Toast.makeText(this, R.string.add_failed, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
