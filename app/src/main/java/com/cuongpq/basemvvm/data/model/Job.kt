package com.cuongpq.basemvvm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "job")
class Job {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id : Int = 0

    @ColumnInfo(name = "job_name")
    @SerializedName("job_name")
    var jobName: String = ""

// //   @ColumnInfo(name = "employer")
//    @SerializedName("employer")
//    lateinit var employer: Employer

    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String = ""

    @ColumnInfo(name = "request")
    @SerializedName("request")
    var request: String = ""

    @ColumnInfo(name = "right")
    @SerializedName("right")
    var right: String = ""


    @ColumnInfo(name = "amount")
    @SerializedName("amount")
    var amount: Int = 0

}