package com.example.tugas13uidesign

import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var tilName: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var etName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var tvPlaceholder: TextView // Tambahan inisialisasi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coordinatorLayout = findViewById(R.id.coordinator_layout)
        tilName = findViewById(R.id.til_name)
        tilEmail = findViewById(R.id.til_email)
        etName = findViewById(R.id.et_name)
        etEmail = findViewById(R.id.et_email)
        tvPlaceholder = findViewById(R.id.tv_placeholder) // Hubungkan ID XML

        val btnSave = findViewById<com.google.android.material.button.MaterialButton>(R.id.btn_save)
        val fabAdd = findViewById<FloatingActionButton>(R.id.fab_add)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val profileCard = findViewById<MaterialCardView>(R.id.profile_card)

        btnSave.setOnClickListener {
            if (validateInput()) {
                Snackbar.make(coordinatorLayout, R.string.snackbar_saved, Snackbar.LENGTH_LONG)
                    .setAction(R.string.snackbar_action_undo) {
                        etName.text?.clear()
                        etEmail.text?.clear()
                    }
                    .show()
            }
        }

        fabAdd.setOnClickListener {
            profileCard.visibility =
                if (profileCard.visibility == android.view.View.VISIBLE) android.view.View.GONE
                else android.view.View.VISIBLE
        }

        // Modifikasi Logika Navigasi Bawah
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_profile -> {
                    // Sembunyikan teks penanda, tampilkan form utama profil
                    tvPlaceholder.visibility = android.view.View.GONE
                    profileCard.visibility = android.view.View.VISIBLE
                    fabAdd.visibility = android.view.View.VISIBLE
                }
                R.id.nav_home -> {
                    // Sembunyikan form profil, tampilkan teks halaman Home
                    profileCard.visibility = android.view.View.GONE
                    fabAdd.visibility = android.view.View.GONE
                    tvPlaceholder.visibility = android.view.View.VISIBLE
                    tvPlaceholder.text = getString(R.string.nav_home)
                }
                R.id.nav_settings -> {
                    // Sembunyikan form profil, tampilkan teks halaman Settings
                    profileCard.visibility = android.view.View.GONE
                    fabAdd.visibility = android.view.View.GONE
                    tvPlaceholder.visibility = android.view.View.VISIBLE
                    tvPlaceholder.text = getString(R.string.nav_settings)
                }
            }
            true
        }
    }

    private fun validateInput(): Boolean {
        var isValid = true

        if (etName.text.isNullOrBlank()) {
            tilName.error = getString(R.string.error_name_empty)
            isValid = false
        } else {
            tilName.error = null
        }

        val email = etEmail.text?.toString().orEmpty()
        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = getString(R.string.error_email_invalid)
            isValid = false
        } else {
            tilEmail.error = null
        }

        return isValid
    }
}