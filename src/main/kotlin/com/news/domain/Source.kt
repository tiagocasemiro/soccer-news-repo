package com.news.domain

import com.google.gson.annotations.SerializedName

data class Source (
	@SerializedName("id") val id : String? = null,
	@SerializedName("name") val name : String,
	@SerializedName("description") val description : String? = null,
	@SerializedName("url") val url : String? = null,
	@SerializedName("category") val category : String? = null,
	@SerializedName("language") val language : String? = null,
	@SerializedName("country") val country : String? = null
)

data class Sources (
	@SerializedName("status") val status : String,
	@SerializedName("totalResults") var totalResults : Int,
	@SerializedName("sources") val sources : List<Source>
)

fun nexoSource() = Source(
	id = "nexo-jornal",
	name = "Nexo Jornal",
	description = "Informação clara e bem explicada você encontra aqui. Nexo, leitura obrigatória para quem quer entender o contexto das principais notícias do Brasil e do mundo.",
	url = "https://www.nexojornal.com.br",
	language = "Português",
	country = "Brasil",
	category = "general")

fun techMundoSource() = Source(
	id = "tec-mundo",
	name = "Novidades do TecMundo",
	description = "Descubra e aprenda tudo sobre tecnologia",
	url = "http://www.tecmundo.com.br",
	category = "Tecnologia",
	language = "Português")

fun theInterceptBrazilSource() = Source(
	id = "the-intercept-brazil",
	name = "The Intercept Brasil",
	description = "O The Intercept Brasil é uma premiada agência de notícias dedicada à responsabilização dos poderosos por meio de um jornalismo destemido e combativo. Suas investigações aprofundadas e suas análises implacáveis se concentram em política, corrupção, meio ambiente, segurança pública, tecnologia, mídia e muito mais. O The Intercept dá aos seus jornalistas a liberdade editorial e o suporte legal de que precisam para expor a corrupção e a injustiça onde quer que as encontrem",
	url = "https://theintercept.com",
	category = "Política",
	language = "Português")

fun elPaisSource() = Source(
	id = "el-pais",
	name = "Edição Brasil no EL PAÍS: o jornal global",
	description = "Últimas notícias do Brasil e do mundo: política, economia, esportes, cultura e sociedade no EL PAÍS. Além disso, especiais, vídeos, fotos, áudios, gráficos e entrevistas com o EL PAÍS.",
	url = "https://brasil.elpais.com",
	category = "General",
	language = "Português",
	country = "Brasil")

fun extraSources() = listOf(nexoSource(), techMundoSource(), theInterceptBrazilSource(), elPaisSource())

