package com.candy.v50

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.candy.v50.ui.theme.Vivo50Theme

@Composable
fun Setting()
{
	Dialog(
		onDismissRequest = {
			/* Todo */
		}
	)
	{
		//设定主题
		Text(
			text = "主题设置",
		)
		Row(

		)
		{
		//主题选项

		}

		HorizontalDivider(

		)

		Text(
			text = "致谢 / API提供",
		)
		Row(
			modifier = Modifier.clickable {
				/* Todo */
			}
		)
		{
			AsyncImage(
				model = ImageRequest.Builder(LocalContext.current)
					.data("https://avatars.githubusercontent.com/u/123456789?v=4")
					.build(),
				contentDescription = "avatar",
				modifier = Modifier.size(20.dp)
					.clip(CircleShape)
			)
			Text(
				text = "KFC-Crazy-Thursday - Github",
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun Setting_Preview()
{
	Vivo50Theme {
		Setting()
	}
}