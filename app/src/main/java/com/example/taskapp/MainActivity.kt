package com.example.taskapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskapp.ui.theme.TaskAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
               MainScreenContent(drawerState = DrawerState(DrawerValue.Open))
                }
            }
        }


@Composable
fun MainScreenContent(drawerState: DrawerState) {
    val scaffoldState = rememberScaffoldState( drawerState = drawerState)
    Scaffold(
       //scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "TaskTodayApp")},
                navigationIcon = {
                    IconButton(onClick = {
                        CoroutineScope(Dispatchers.Default).launch {
                            scaffoldState.drawerState.open()}
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Drawer Menu")
                    }
                }
            )
        },
        drawerBackgroundColor = Color.Red,
        drawerGesturesEnabled = drawerState.isOpen,
        drawerContent = {
                //DRAWER HEADER
                Box(
                    modifier = Modifier
                        .background(Color.Magenta)
                        .height(20.dp)
                ){
                    Column() {
                    Text(text = "Opções!!!")
                    }
                }
            Spacer(modifier = Modifier.height(16.dp))
            //DRAWER CONTENT
            Column() {
                Text(text = "Opção de Menu 1")
                Text(text = "Opção de Menu 2")
                Text(text = "Opção de Menu 3")
            }
        },
        content = {
            paddingValues ->  Log.i("paddingValues","$paddingValues")

            Column(
                modifier = Modifier
                    .background(Color.Cyan)
                    .fillMaxSize()
            ) {
                MySearchField(modificador = Modifier
                    .fillMaxWidth()
                    .background(Color.Black))
                MytaskWidget(
                    modificador = Modifier.fillMaxWidth(),
                    taskname = "Preparar Aula LazyList/LazyColmn",
                    taskDetails = "É bem melhor usar lazilist ao inves de column",
                    deadEndDate = Date()
                )
                MytaskWidget(
                    modificador = Modifier.fillMaxWidth(),
                    taskname = "Prova de Matematica",
                    taskDetails = "Estudar Calculo captulo 1 e 2",
                    deadEndDate = Date()
                )

            }
        },
        bottomBar = {
            BottomAppBar(
                content = { Text(text = "Developer By Rafael(SkyNet)")}
            )

        }
    )//Scaffold
}//fun MainScreenContent()

@Composable
fun MySearchField(modificador: Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        modifier = modificador,
    placeholder = { Text(text = "Pesquisar tarefas", color=Color.White)},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Seach Icon")

        }
    )
}

@Composable
fun MytaskWidget(
                modificador: Modifier,
                taskname: String,
                taskDetails: String,
                deadEndDate: Date
                 ){
    val dateFormatter = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault())
    Row(modifier = modificador) {
        Column() {
            Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Icons of a pendent task")
            Text(
                text = dateFormatter.format(deadEndDate),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 12.sp
            )
        }
    Column(modifier = Modifier
        .border(width = 1.dp, color = Color.Black)
        .padding(3.dp)
    ) {
        Text(
            text= taskname,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
            )
        Text(
            text = taskDetails,
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MainScreenContent(drawerState = DrawerState(DrawerValue.Closed))
}