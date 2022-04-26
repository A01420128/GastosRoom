package edu.itesm.gastos.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import edu.itesm.gastos.R
import edu.itesm.gastos.databinding.ActivityCapturaGastoBinding
import edu.itesm.gastos.entities.Gasto

class CapturaGastoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCapturaGastoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCapturaGastoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.agregaGasto.setOnClickListener {
            val concepto = binding.conceptoGastoCifra.text.toString()
            val monto = binding.montoGastoCifra.text.toString().toDouble()
            if (concepto == "" || monto == 0.0) {
                Toast.makeText(this, "Wrong concepto or monto.", Toast.LENGTH_LONG).show()
            } else {
                val gasto = Gasto(0, concepto, monto)
                val intent = Intent()
                intent.putExtra("gasto", gasto)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}