package org.d3if0024.mobproassement.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.d3if0024.mobproassement.R
import org.d3if0024.mobproassement.model.Pesanan
import org.d3if0024.mobproassement.ui.theme.MobproAssesment2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    Scaffold (
        topBar = {
            TopAppBar(title = {
                Text(text = (stringResource(id = R.string.app_name)))
            },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }

}

@Composable
fun ScreenContent(modifier: Modifier){
    val viewModel : MainViewModel = viewModel()
    val data =  viewModel.data
    val context = LocalContext.current


    if (data.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(

                painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
                contentDescription = "tidak ada pesanan",
                modifier = Modifier.padding(16.dp).size(100.dp,100.dp),
                tint = (Color(0xFFE7E7E7))

            )
            Text(text = stringResource(id = R.string.list_kosong))

        }
    }
    else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            items(data) {
                ListItem(pesanan = it) {
                val pesan = context.getString(R.string.x_diklik, it.size)
                    Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show()
                }
                Divider()
            }
        }
    }
}

@Composable
fun ListItem(pesanan: Pesanan, onClick: () -> Unit ){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = pesanan.size,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = pesanan.topping,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = pesanan.drink,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview(){
    MobproAssesment2Theme {
        MainScreen()
    }
}