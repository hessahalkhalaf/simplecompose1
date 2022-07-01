package com.hessah.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hessah.composetutorial.ui.theme.ComposeTutorialTheme
//?#
import androidx.compose.foundation.clickable
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                Conversation(SampleData.conversationSample)
            }
        }
    }
}

data class MessageDDCC(val author: String, val body: String)


@Composable
fun MessageCard(msgPARAMETER: com.hessah.composetutorial.MessageDDCC) {

    Row {
        Image(
           painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Contact profile picture "
       , modifier = Modifier
                    // set image size to 40 db
                .size(40.dp)
                // Clip(cut) : image to be shaped as a circle ##
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        ////// add a horizontal space between the image and the column ##
        Spacer(modifier = Modifier.width(8.dp))

        // i keep track if the message is expanded or not (this variable ##
        var isExpanded by remember { mutableStateOf(false)}
        // surfaceColor will be updated gradually from one color to the other????
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )



        //we toggle the isExpanded variable when we click on the column ##
        Column( modifier = Modifier.clickable { isExpanded=!isExpanded } )
        {
            Text(
                text = msgPARAMETER.author
             , color = MaterialTheme.colors.secondaryVariant
             , style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface (
                shape = MaterialTheme.shapes.medium, elevation = 1.dp ,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier.animateContentSize().padding(1.dp))
                 //       block Surface ##  >>
                   {
                Text(
                    text = msgPARAMETER.body,
                    modifier = Modifier.padding(all = 4.dp),

                    // if the message is expanded : we display all its content
                    //otherwise only display the first line 1 ##
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            } // end block surface
        }
    }
}


//@Preview (name = "Light Mode")
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode "
//)


@Composable
fun PreviewMessageCard() {
    ComposeTutorialTheme {
        MessageCard(
            msgPARAMETER = MessageDDCC("Android", "Hey, take a look at Jetpack Compose, it's great!")
        )
    }
}


//###################################################

@Composable
fun Conversation(messagesCC: List<MessageDDCC>) {
    //for  list messages use (lazy)
    LazyColumn {
        items(messagesCC) { it ->
            MessageCard(it)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    ComposeTutorialTheme {
        Conversation(SampleData.conversationSample)
    }
}





object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        MessageDDCC(
            "Colleague",
            "Test...Test...Test..."
        ),
        MessageDDCC(
            "Colleague",
            "List of Android versions:\n" +
                    "Android KitKat (API 19)\n" +
                    "Android Lollipop (API 21)\n" +
                    "Android Marshmallow (API 23)\n" +
                    "Android Nougat (API 24)\n" +
                    "Android Oreo (API 26)\n" +
                    "Android Pie (API 28)\n" +
                    "Android 10 (API 29)\n" +
                    "Android 11 (API 30)\n" +
                    "Android 12 (API 31)\n"
        ),
        MessageDDCC(
            "Colleague",
            "I think Kotlin is my favorite programming language.\n" +
                    "It's so much fun!"
        ),
        MessageDDCC(
            "Colleague",
            "Searching for alternatives to XML layouts..."
        ),
        MessageDDCC(
            "Colleague",
            "Hey, take a look at Jetpack Compose, it's great!\n" +
                    "It's the Android's modern toolkit for building native UI." +
                    "It simplifies and accelerates UI development on Android." +
                    "Less code, powerful tools, and intuitive Kotlin APIs :)"
        ),
        MessageDDCC(
            "Colleague",
            "It's available from API 21+ :)"
        ),
        MessageDDCC(
            "Colleague",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        MessageDDCC(
            "Colleague",
            "Android Studio next version's name is Arctic Fox"
        ),
        MessageDDCC(
            "Colleague",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        MessageDDCC(
            "Colleague",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        MessageDDCC(
            "Colleague",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        MessageDDCC(
            "Colleague",
            "Previews are also interactive after enabling the experimental setting"
        ),
        MessageDDCC(
            "Colleague",
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}

