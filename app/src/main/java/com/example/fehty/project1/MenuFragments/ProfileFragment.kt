package com.example.fehty.project1.MenuFragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.fehty.project1.Activity.MainActivity
import com.example.fehty.project1.R
import com.facebook.*
import com.facebook.AccessToken
import com.facebook.internal.ImageRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.json.JSONException


@SuppressLint("ValidFragment")
class ProfileFragment(private val mainActivity: MainActivity?) : Fragment() {

    private var callbackManager = CallbackManager.Factory.create()!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.setReadPermissions("email")
        loginButton.fragment = this

        LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        importFbProfilePhoto()
                    }

                    override fun onCancel() {
                        textView.text = "Login is cancelled"
                    }

                    override fun onError(exception: FacebookException) {
                        textView.text = "Login Error"
                    }
                })

        importFbProfilePhoto()

        accessTokenTracker
    }

//        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
//            override fun onSuccess(result: LoginResult?) {
//                textView.text = "Login is succeed ${result!!.accessToken.userId} ${result.accessToken.token}"
//            }
//
//            override fun onCancel() {
//                textView.text = "Login is cancelled"
//            }
//
//            override fun onError(error: FacebookException?) {
//                textView.text = "Login Error"
//            }
//
//        })


    var accessTokenTracker = object : AccessTokenTracker() {
        override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken?) {
            if (currentAccessToken == null) {
                textView.text = "You have logged out"
                userPhoto.setImageResource(android.R.color.transparent)
            } else if (currentAccessToken != null) {
                LoginManager.getInstance().registerCallback(callbackManager,
                        object : FacebookCallback<LoginResult> {
                            override fun onSuccess(loginResult: LoginResult) {
                                importFbProfilePhoto()
                            }

                            override fun onCancel() {
                                textView.text = "Login is cancelled"
                            }

                            override fun onError(exception: FacebookException) {
                                textView.text = "Login Error"
                            }
                        })

                importFbProfilePhoto()

            }

        }
    }

    private fun importFbProfilePhoto() {
//        if (AccessToken.getCurrentAccessToken() == null) {
//            textView.text = "Login is cancelled"
//        }

        if (AccessToken.getCurrentAccessToken() != null) {
            val request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken()) { me, response ->
                if (AccessToken.getCurrentAccessToken() != null) {
                    if (me != null) {
                        val profileImageUrl = ImageRequest.getProfilePictureUri(me.optString("id"), 500, 500)
                        Picasso.get().load(profileImageUrl).into(userPhoto)
                        textView.text = response.jsonObject.getString("name")
                    }
                }
            }
            GraphRequest.executeBatchAsync(request)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            exitFromAdditionItems()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun exitFromAdditionItems() {
        mainActivity!!.floatingActionButton.show()
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        fragmentManager
                ?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(R.id.container, ListFragment())
                ?.commit()
    }

    private fun setFacebookData(loginResult: LoginResult) {
        val request = GraphRequest.newMeRequest(loginResult.accessToken) { _, response ->
            try {
                Log.e("Response", response.toString())

                val email = response.jsonObject.getString("email")
                val firstName = response.jsonObject.getString("first_name")
                val lastName = response.jsonObject.getString("last_name")
                val gender = response.jsonObject.getString("gender")

                val profile = Profile.getCurrentProfile()
                val id = profile.id
                val link = profile.linkUri.toString()
                Log.e("Link", link)
                if (Profile.getCurrentProfile() != null) {
                    Log.e("Login", "ProfilePic" + Profile.getCurrentProfile().getProfilePictureUri(200, 200))
                }

                Log.e("Login" + "Email", email)
                Log.e("Login" + "FirstName", firstName)
                Log.e("Login" + "LastName", lastName)
                Log.e("Login" + "Gender", gender)


            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        val parameters = Bundle()
        parameters.putString("fields", "id,email,first_name,last_name,gender")
        request.parameters = parameters
        request.executeAsync()
    }

}