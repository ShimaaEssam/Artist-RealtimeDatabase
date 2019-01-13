package com.shimaa.firebaserealtimedatabase

import android.net.sip.SipSession
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot


class MainActivity : AppCompatActivity() {

    lateinit var  AName:EditText
    lateinit var spinner:Spinner
    lateinit var addArtist:Button
    lateinit var DBArtists:DatabaseReference
    lateinit var listView: ListView
    lateinit var listartists:MutableList<Artist>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AName=(findViewById(R.id.NameText))as EditText
        spinner=(findViewById(R.id.spinner))as Spinner
        addArtist=(findViewById(R.id.Addbtn))as Button
        DBArtists=FirebaseDatabase.getInstance().getReference("artists")
        listView=(findViewById(R.id.listview))as ListView
        listartists = ArrayList()


        addArtist.setOnClickListener({
            Add_Artist()
        })

       listView.setOnItemClickListener { parent, view, position, id ->

           var artist:Artist=listartists.get(position)
           ShowUpdateDialog(artist.getId(),artist.getName())

       }

    }

    //update Name
    private  fun  ShowUpdateDialog( artistId:String,artistName:String) {
        var dialogBuilder:AlertDialog.Builder=AlertDialog.Builder(this)
        var inflater:LayoutInflater =layoutInflater
        var dialogview:View=inflater.inflate(R.layout.update_dialog,null)
        dialogBuilder.setView(dialogview)
        var Name:EditText=dialogview.findViewById(R.id.NameEditText)
        var updateBtn:Button=dialogview.findViewById(R.id.update)
        var spinner:Spinner=dialogview.findViewById(R.id.spinnerupdate)

        updateBtn.setOnClickListener({

        })

        dialogBuilder.setTitle("updated artist " +artistName)
        var alertDialog:AlertDialog=dialogBuilder.create()
        alertDialog.show()


    }


    //Retrieve Data
    override fun onStart() {
        super.onStart()
        DBArtists.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var artistSnapShot:DataSnapshot
                var art:Artist
                listView.removeAllViewsInLayout()
                listartists.clear()
                for(artistSnapShot in dataSnapshot.children){
                    art= artistSnapShot.getValue(Artist::class.java)!!
                    listartists.add(art)

                }
                var adapter:ArtistListAdapter=ArtistListAdapter(this@MainActivity,listartists)
                listView.adapter=adapter


            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    //Saving Data
    private  fun  Add_Artist(){
        var name= NameText.text.toString().trim()
        var genre:String=spinner.selectedItem.toString()

        if(!TextUtils.isEmpty(name)){
            //store it
            var id:String=DBArtists.push().key
            var artist= Artist(id,name,genre)
            DBArtists.child(id).setValue(artist)
            Toast.makeText(this@MainActivity,"Artist Added",Toast.LENGTH_LONG).show()


        }
        else{
            Toast.makeText(this@MainActivity,"You Should Enter a name",Toast.LENGTH_LONG).show()
        }

    }
}
