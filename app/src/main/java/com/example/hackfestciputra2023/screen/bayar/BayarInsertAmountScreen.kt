package com.example.hackfestciputra2023.screen.bayar

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hackfestciputra2023.component.AppButton
import com.example.hackfestciputra2023.component.AppText
import com.example.hackfestciputra2023.component.AppTopBarMidTitle
import com.example.hackfestciputra2023.data.remote_source.Resource
import com.example.hackfestciputra2023.ui.theme.AppColor
import com.example.hackfestciputra2023.ui.theme.AppType
import com.example.hackfestciputra2023.util.NavRoute
import com.example.hackfestciputra2023.viewmodel.RootViewModel
import com.example.hackfestciputra2023.viewmodel.bayar.BayarInsertAmountViewModel
import com.ngikut.u_future.component.AppTextInputNormal
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BayarInputAmountScreen(
    navController: NavController,
    rootViewModel: RootViewModel,
    menu: String
) {
    val viewModel = hiltViewModel<BayarInsertAmountViewModel>()
    val prosesBayarState = viewModel.prosesBayarState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = prosesBayarState.value) {
        when (prosesBayarState.value) {
            is Resource.Error -> {/*TODO*/
            }

            is Resource.Loading -> {/*TODO*/
            }

            is Resource.Success -> {
                prosesBayarState.value.data?.let {
                    rootViewModel.xenditUrl.value = it.data.invoice_url
                    delay(1500)
//                    val webIntent: Intent = Uri.parse(it.data.invoice_url).let { webpage ->
//                        Intent(Intent.ACTION_VIEW, webpage)
//                    }
//                    context.startActivity(webIntent)
                    navController.popBackStack()
                    navController.navigate(NavRoute.WEBVIEW.name)
                }
            }
        }
    }

    Scaffold(topBar = {
        AppTopBarMidTitle(onBackClicked = { navController.popBackStack() }, title = "Bayar Pesanan")
    }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(250.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(AppColor.grey100)
                    )

                    AppText(text = menu, style = AppType.h2)
                }

                AppTextInputNormal(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    placeHolder = "Masukkan harga",
                    value = viewModel.amountValue.value,
                    onValueChange = { viewModel.amountValue.value = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    onClick = {
                        viewModel.prosesPembayaran(menu)
                    },
                    text = "Proses Pembayaran"
                )
            }
        }
    }
}