package co.aladinjunior.instagram.search.data

import android.os.Handler
import android.os.Looper
import co.aladinjunior.instagram.commom.base.BaseCallback
import co.aladinjunior.instagram.commom.model.Database
import co.aladinjunior.instagram.commom.model.UserAuth

class SearchFakeRemoteDataSource : SearchDataSource {

    override fun fetchUsers(name: String, callback: BaseCallback<List<UserAuth>>) {


        val users = Database.userAuth.filter {
            it.name.lowercase()
                .startsWith(name.lowercase()) && it.uuid != Database.userSession!!.uuid
        }

        if (users.isEmpty()) callback.onFailure("usuario nao encontrado")


        callback.onSuccess(users.toList())


    }
}
