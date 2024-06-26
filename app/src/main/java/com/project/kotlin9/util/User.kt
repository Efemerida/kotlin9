package com.project.kotlin9.util

import com.google.firebase.firestore.Blob

class User(var username: String? = null,
           var usersurname: String? = null,
           var login: String? = null,
           var password: String? = null,
           var image:Blob? = null,
           var date:String? = null
){
    data class Builder(
        private var username: String? = null,
        private var usersurname: String? = null,
        private var login: String? = null,
        private var password: String? = null,
        private var image:Blob? =null,
        private var date: String?=null) {

        fun username(username: String) = apply { this.username = username }
        fun usersurname(usersurname: String) = apply { this.usersurname = usersurname }
        fun login(login: String) = apply { this.login = login }
        fun password(password: String) = apply { this.password = password }
        fun date(date: String) = apply { this.date = date }
        fun image(image: ByteArray) = apply { this.image = Blob.fromBytes(image) }
        fun build() = User(username, usersurname, login, password, image,date)
    }

    override fun toString(): String {
        return "User(username=$username, usersurname=$usersurname, login=$login, password=$password, image=$image, date=$date)"
    }

}