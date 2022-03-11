package com.hot.pocketdoctor.presentation.reports

data class ReportsList(
    var reports : List<Report>
    ) {
    data class Report(
        var doctor: String,
        var reportTime: String,
        var symptom: String,
        var hospital: String
    )
}
