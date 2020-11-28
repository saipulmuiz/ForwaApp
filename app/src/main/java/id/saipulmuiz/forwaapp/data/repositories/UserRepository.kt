package id.saipulmuiz.forwaapp.data.repositories

import javax.inject.Inject

// User repository; Keyword : Repository
class UserRepository @Inject constructor() {

    // Function : for check is data stored in database ?
    // fun checkFavoriteUser(userId: Int, context: Context): LiveData<Status<UserEntity>> {
    //     val liveData = MutableLiveData<Status<UserEntity>>()
    //
    //     val uri = "$USER_CONTENT_URI/$userId".toUri()
    //     val cursor = context.contentResolver
    //         .query(uri, null, null, null, null)
    //
    //     cursor?.let {
    //         if (cursor.moveToFirst()) {
    //             liveData.postValue(Status.success(it.toUserEntity()))
    //         } else {
    //             liveData.postValue(Status.error("Error", null))
    //         }
    //
    //         cursor.close()
    //     }
    //
    //     return liveData
    // }
    //
    // // Function : for save data into database
    // fun addFavoriteUser(user: UserEntity, context: Context): LiveData<Long> {
    //     val liveData = MutableLiveData<Long>()
    //
    //     val cursor =
    //         context.contentResolver.insert(USER_CONTENT_URI.toUri(), user.toContentValues())
    //
    //     cursor?.let { liveData.postValue(1) }
    //
    //     return liveData
    // }
    //
    // // Function : for delete data in database
    // fun deleteFavoriteUser(user: UserEntity, context: Context): LiveData<Long> {
    //     val liveData = MutableLiveData<Long>()
    //
    //     val uri = "$USER_CONTENT_URI/${user.id}".toUri()
    //     val cursor =
    //         context.contentResolver.delete(uri, null, null)
    //
    //     cursor.let { liveData.postValue(it.toLong()) }
    //
    //     return liveData
    // }
    //
    // // Function : for get all users from database
    // fun getUsers(context: Context): LiveData<Status<List<UserEntity>>> {
    //     val liveData = MutableLiveData<Status<List<UserEntity>>>()
    //
    //     val cursor = context.contentResolver
    //         .query(USER_CONTENT_URI.toUri(), null, null, null, null)
    //     cursor?.let {
    //         liveData.postValue(Status.success(it.toListUserEntity()))
    //
    //         cursor.close()
    //     }
    //
    //     return liveData
    // }
}