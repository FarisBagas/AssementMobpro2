package org.d3if0024.mobproassement.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0024.mobproassement.R
import org.d3if0024.mobproassement.database.PesananDb
import org.d3if0024.mobproassement.ui.theme.MobproAssesment2Theme
import org.d3if0024.mobproassement.util.ViewModelFactory

const val KEY_ID_PESANAN ="idPesanan"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id:Long? = null) {
    val context = LocalContext.current
    val db = PesananDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    // daftar pilihan size
    val sizeOption = listOf(
        stringResource(id = R.string.small),
        stringResource(id = R.string.reguler),
        stringResource(id = R.string.large)
    )

    // daftar pilihan toping
    val toppingOption = listOf(
        stringResource(id = R.string.keju),
        stringResource(id = R.string.daging),
        stringResource(id = R.string.pepperoni),
        stringResource(id = R.string.sayuran),
        stringResource(id = R.string.seafood)
    )

    // daftar pilihan minuman
    val drinkOption = listOf(
        stringResource(id = R.string.cola),
        stringResource(id = R.string.tea),
        stringResource(id = R.string.milo),
        stringResource(id = R.string.juice)
    )

    // var simpan data
    var pilihanSize by rememberSaveable { mutableStateOf(sizeOption[0]) }
    var pilihanToping by rememberSaveable { mutableStateOf(toppingOption[0]) }
    var pilihanDrink by rememberSaveable { mutableStateOf(drinkOption[0]) }

    LaunchedEffect(true) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getPesanan(id) ?: return@LaunchedEffect
        pilihanSize = data.size
        pilihanToping = data.topping
        pilihanDrink = data.drink
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                IconButton(onClick = {navController.popBackStack()}) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.kembali),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
                title = {
                    if (id == null)
                        Text(text = stringResource(id = R.string.tambah_pesanan))
                    else
                        Text(text = stringResource(id = R.string.ubah_pesanan))

                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        if (id == null){
                            viewModel.insert(pilihanSize,pilihanToping,pilihanDrink)
                        } else {
                            viewModel.insert(pilihanSize,pilihanToping,pilihanDrink)
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = stringResource(id = R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { padding ->
        FormPesanan(
            pilihanSize = pilihanSize,
            pilihanSizeChange = { pilihanSize = it },
            pilihanToping = pilihanToping,
            pilihanTopingChange = { pilihanToping = it },
            pilihanDrink = pilihanDrink,
            pilihanDrinkChange = { pilihanDrink = it },
            radioOptionSize = sizeOption,
            radioOptionToping = toppingOption,
            radioOptionDrink = drinkOption,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormPesanan(
    pilihanSize: String, pilihanSizeChange: (String) -> Unit,
    pilihanToping: String, pilihanTopingChange: (String) -> Unit,
    pilihanDrink: String, pilihanDrinkChange: (String) -> Unit,
    radioOptionSize: List<String>,
    radioOptionToping: List<String>,
    radioOptionDrink: List<String>,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // row Size
        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOptionSize.forEach { option ->
                RedioOption(
                    label = option,
                    isSelected = pilihanSize == option,
                    modifier = Modifier
                        .selectable(
                            selected = pilihanSize == option,
                            onClick = { pilihanSizeChange(option) },
                            role = Role.RadioButton
                        )
                        .padding(8.dp)
                )
            }
        }
        // kolom Toping
        Column (
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ){
            radioOptionToping.forEach { option ->
                RedioOption(
                    label = option,
                    isSelected = pilihanToping == option,
                    modifier = Modifier
                        .selectable(
                            selected = pilihanToping == option,
                            onClick = { pilihanTopingChange(option) },
                            role = Role.RadioButton
                        )
                        .padding(8.dp)
                )
            }
        }
        // row Minuman
        Row (
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ){
            radioOptionDrink.forEach { option ->
                RedioOption(
                    label = option,
                    isSelected = pilihanDrink == option,
                    modifier = Modifier
                        .selectable(
                            selected = pilihanSize == option,
                            onClick = { pilihanDrinkChange(option) },
                            role = Role.RadioButton
                        )
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun RedioOption(label: String, isSelected: Boolean, modifier: Modifier) {
    Row(
        modifier = modifier,

        ) {
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    MobproAssesment2Theme {
        DetailScreen(rememberNavController())
    }
}