package com.cuongpq.basemvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cuongpq.basemvvm.data.model.UserExample
//******************************
//******************************
//***** Create by cuongpq  *****
//******************************
//******************************

@Dao
interface UserDao {
    @Query(value = "SELECT * FROM User WHERE User.id=:userId")
    fun findUserById(userId: Int) : UserExample

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(item: UserExample): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListUser(item: MutableList<UserExample>)

    @Query(value = "SELECT * FROM User")
    fun findAll(): MutableList<UserExample>
}