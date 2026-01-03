package com.example.eebb.ui.model

data class Event(
    val title: String,
    val schedule: String,
    val location: String,
    val description: String,
    val highlight: Boolean = false
)

data class Sermon(
    val title: String,
    val speaker: String,
    val duration: String,
    val publishedOn: String,
    val tag: String = ""
)

data class Highlight(
    val title: String,
    val detail: String
)

data class Ministry(
    val name: String,
    val focus: String
)

object SampleContent {
    val heroVerse = "L’Éternel est mon berger : je ne manquerai de rien." to "Psaume 23:1"

    val featuredEvent = Event(
        title = "Culte en présentiel + Live",
        schedule = "Dimanche | 11h00",
        location = "7012 1ère Ave, Montréal",
        description = "Louange, enseignement et prière pour toute la famille.",
        highlight = true
    )

    val upcomingEvents = listOf(
        featuredEvent,
        Event(
            title = "Soirée de prière",
            schedule = "Mercredi | 19h30",
            location = "Salle de prière",
            description = "Intercession pour l’église, la ville et les besoins personnels."
        ),
        Event(
            title = "Jeunesse Bethesda",
            schedule = "Vendredi | 20h00",
            location = "Espace jeunesse",
            description = "Groupes de partage, ateliers et louange avec l’équipe jeunesse."
        )
    )

    val sermons = listOf(
        Sermon(
            title = "Espérance vivante",
            speaker = "Pasteur principal",
            duration = "32 min",
            publishedOn = "Dimanche 10 nov.",
            tag = "Série actuelle"
        ),
        Sermon(
            title = "Vivre la foi en semaine",
            speaker = "Pasteur associé",
            duration = "27 min",
            publishedOn = "Dimanche 3 nov.",
            tag = "Podcast + vidéo"
        ),
        Sermon(
            title = "Dieu est fidèle",
            speaker = "Invité",
            duration = "30 min",
            publishedOn = "Dimanche 27 oct.",
            tag = "Témoignage"
        )
    )

    val highlights = listOf(
        Highlight("Confession de foi", "Christ au centre, la Bible comme autorité"),
        Highlight("Hospitalité", "Accueil chaleureux et accompagnement personnalisé"),
        Highlight("Famille", "Espaces dédiés aux enfants et aux ados"),
        Highlight("Mission urbaine", "Actions solidaires et témoignage dans la ville")
    )

    val ministries = listOf(
        Ministry("Louange", "Une ambiance chaleureuse et engagée"),
        Ministry("Jeunesse", "Mentorat, groupes de vie et créativité"),
        Ministry("Accueil", "Prendre soin dès le premier contact"),
        Ministry("Médias & Live", "Diffusion YouTube et podcasts"),
        Ministry("Visites", "Soutien pastoral et prière"),
        Ministry("Mission", "Service de la ville et partenariats")
    )
}
