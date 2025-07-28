package com.kamikadze328.nasadigest.ui.features.pictureoftheday

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.ImageLoader
import coil3.compose.SubcomposeAsyncImage
import coil3.video.VideoFrameDecoder
import com.kamikadze328.nasadigest.R
import com.kamikadze328.nasadigest.ui.common.ErrorScreenUi
import com.kamikadze328.nasadigest.ui.common.LoadingScreenUi
import com.kamikadze328.nasadigest.ui.common.prettyPrint
import com.kamikadze328.nasadigest.ui.features.pictureoftheday.model.PictureOfTheDayUiState
import com.kamikadze328.nasadigest.ui.theme.NasaDigestTheme
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat

@Composable
fun PictureOfTheDayScreenUi(
    viewModel: PictureOfTheDayViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    PictureOfTheDayScreenUi(
        uiState = uiState,
        onEvent = remember { viewModel::onEvent },
    )
}

@Composable
private fun PictureOfTheDayScreenUi(
    uiState: PictureOfTheDayUiState,
    onEvent: (PictureOfTheDayUiEvent) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        when (uiState) {
            is PictureOfTheDayUiState.Loading -> {
                LoadingScreenUi(modifier = Modifier.fillMaxSize())
            }

            is PictureOfTheDayUiState.Error -> {
                ErrorScreenUi(
                    modifier = Modifier.fillMaxSize(),
                    text = uiState.message,
                    onRefresh = { onEvent.invoke(PictureOfTheDayUiEvent.OnRefresh) },
                )
            }

            is PictureOfTheDayUiState.Success -> {
                PictureOfTheDayDataUi(uiState = uiState)
            }
        }
    }
}

@Composable
private fun PictureOfTheDayDataUi(
    uiState: PictureOfTheDayUiState.Success
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .verticalScroll(state = rememberScrollState())
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = uiState.title.orEmpty(),
                style = MaterialTheme.typography.headlineLarge,
            )

            Text(
                textAlign = TextAlign.Center,
                text = uiState.date.prettyPrint(),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Box {
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = uiState.url,
                imageLoader = ImageLoader.Builder(LocalContext.current)
                    .components { add(VideoFrameDecoder.Factory()) }
                    .build(),
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                            .size(48.dp),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                },
                error = {
                    val uriHandler = LocalUriHandler.current
                    Button(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(horizontal = 16.dp),
                        onClick = {
                            val dateFormat = DateTimeFormat.forPattern("yyMMdd")
                            val formattedDate = uiState.date.toString(dateFormat)
                            val url = "https://apod.nasa.gov/apod/ap$formattedDate.html"
                            uriHandler.openUri(url)
                        },

                        ) {
                        val text = stringResource(id = R.string.picture_of_the_day_open_in_browser)
                        Icon(
                            modifier = Modifier.width(24.dp),
                            imageVector = Icons.Default.Search,
                            contentDescription = text,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                },
                contentDescription = uiState.title,
            )
            if (!uiState.copyright.isNullOrEmpty()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(4.dp),
                    text = uiState.copyright,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = uiState.explanation.orEmpty(),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
@Preview
private fun PictureOfTheDayScreenUiPreview() {
    NasaDigestTheme {
        PictureOfTheDayScreenUi(
            uiState = PictureOfTheDayUiState.Success(
                title = "Title",
                explanation = "Explanation",
                copyright = "copyright",
                mediaType = PictureOfTheDayUiState.Success.MediaType.IMAGE,
                url = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
                date = LocalDate(),
            ),
            onEvent = {}
        )
    }
}

