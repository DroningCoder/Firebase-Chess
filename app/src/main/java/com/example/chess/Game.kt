package com.example.chess

import android.content.ClipData.Item
import android.content.Intent
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.traceEventEnd
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.selects.select
import java.util.ArrayList
import kotlin.math.PI

class Game : AppCompatActivity() {

    private var board : ArrayList<ArrayList<String>> = arrayListOf(
        arrayListOf("","","","","","","",""),
        arrayListOf("","","","","","","",""),
        arrayListOf("","","","","","","",""),
        arrayListOf("","","","","","","",""),
        arrayListOf("","","","","","","",""),
        arrayListOf("","","","","","","",""),
        arrayListOf("","","","","","","",""),
        arrayListOf("","","","","","","","")
    )

    private lateinit var player1Image:ImageView
    private lateinit var player1Username:TextView
    private lateinit var player1Timer:TextView

    private lateinit var player2Image:ImageView
    private lateinit var player2Username:TextView
    private lateinit var player2Timer:TextView

    private lateinit var a8: ImageView
    private lateinit var b8: ImageView
    private lateinit var c8: ImageView
    private lateinit var d8: ImageView
    private lateinit var e8: ImageView
    private lateinit var f8: ImageView
    private lateinit var g8: ImageView
    private lateinit var h8: ImageView
    private lateinit var a7: ImageView
    private lateinit var b7: ImageView
    private lateinit var c7: ImageView
    private lateinit var d7: ImageView
    private lateinit var e7: ImageView
    private lateinit var f7: ImageView
    private lateinit var g7: ImageView
    private lateinit var h7: ImageView
    private lateinit var a6: ImageView
    private lateinit var b6: ImageView
    private lateinit var c6: ImageView
    private lateinit var d6: ImageView
    private lateinit var e6: ImageView
    private lateinit var f6: ImageView
    private lateinit var g6: ImageView
    private lateinit var h6: ImageView
    private lateinit var a5: ImageView
    private lateinit var b5: ImageView
    private lateinit var c5: ImageView
    private lateinit var d5: ImageView
    private lateinit var e5: ImageView
    private lateinit var f5: ImageView
    private lateinit var g5: ImageView
    private lateinit var h5: ImageView
    private lateinit var a4: ImageView
    private lateinit var b4: ImageView
    private lateinit var c4: ImageView
    private lateinit var d4: ImageView
    private lateinit var e4: ImageView
    private lateinit var f4: ImageView
    private lateinit var g4: ImageView
    private lateinit var h4: ImageView
    private lateinit var a3: ImageView
    private lateinit var b3: ImageView
    private lateinit var c3: ImageView
    private lateinit var d3: ImageView
    private lateinit var e3: ImageView
    private lateinit var f3: ImageView
    private lateinit var g3: ImageView
    private lateinit var h3: ImageView
    private lateinit var a2: ImageView
    private lateinit var b2: ImageView
    private lateinit var c2: ImageView
    private lateinit var d2: ImageView
    private lateinit var e2: ImageView
    private lateinit var f2: ImageView
    private lateinit var g2: ImageView
    private lateinit var h2: ImageView
    private lateinit var a1: ImageView
    private lateinit var b1: ImageView
    private lateinit var c1: ImageView
    private lateinit var d1: ImageView
    private lateinit var e1: ImageView
    private lateinit var f1: ImageView
    private lateinit var g1: ImageView
    private lateinit var h1: ImageView

    private lateinit var grid: GridLayout

    val firestore = FirebaseFirestore.getInstance()

    var player1online="offline"
    var player2online="offline"

    var turn = ""

    var player = ""

    var white = ""

    var game_id=""

    var gameId = ""

    var userId = ""

    var drawResignDocumentId=""

    var email=""

    var usernameplayer1=""
    var usernameplayer2=""

    var drawOffered=false
    var resign=false

    var king_moved=0
    var left_rook_moved=0
    var right_rook_moved=0

    var points=0
    var pointsOpp=0

    private fun setBoard(time:Int) {

        if (white==player) {

            grid.setBackgroundResource(R.drawable.board)

            a2.setImageResource(R.drawable.pawn_white)
            b2.setImageResource(R.drawable.pawn_white)
            c2.setImageResource(R.drawable.pawn_white)
            d2.setImageResource(R.drawable.pawn_white)
            e2.setImageResource(R.drawable.pawn_white)
            f2.setImageResource(R.drawable.pawn_white)
            g2.setImageResource(R.drawable.pawn_white)
            h2.setImageResource(R.drawable.pawn_white)
            a1.setImageResource(R.drawable.rook_white)
            h1.setImageResource(R.drawable.rook_white)
            b1.setImageResource(R.drawable.knight_white)
            g1.setImageResource(R.drawable.knight_white)
            c1.setImageResource(R.drawable.bishop_white)
            f1.setImageResource(R.drawable.bishop_white)
            e1.setImageResource(R.drawable.king_white)
            d1.setImageResource(R.drawable.queen_white)

            a7.setImageResource(R.drawable.pawn_black)
            b7.setImageResource(R.drawable.pawn_black)
            c7.setImageResource(R.drawable.pawn_black)
            d7.setImageResource(R.drawable.pawn_black)
            e7.setImageResource(R.drawable.pawn_black)
            f7.setImageResource(R.drawable.pawn_black)
            g7.setImageResource(R.drawable.pawn_black)
            h7.setImageResource(R.drawable.pawn_black)
            a8.setImageResource(R.drawable.rook_black)
            h8.setImageResource(R.drawable.rook_black)
            b8.setImageResource(R.drawable.knight_black)
            g8.setImageResource(R.drawable.knight_black)
            c8.setImageResource(R.drawable.bishop_black)
            f8.setImageResource(R.drawable.bishop_black)
            e8.setImageResource(R.drawable.king_black)
            d8.setImageResource(R.drawable.queen_black)

            board[6][0]="pawn_white"
            board[6][1]="pawn_white"
            board[6][2]="pawn_white"
            board[6][3]="pawn_white"
            board[6][4]="pawn_white"
            board[6][5]="pawn_white"
            board[6][6]="pawn_white"
            board[6][7]="pawn_white"
            board[7][0]="rook_white"
            board[7][1]="knight_white"
            board[7][2]="bishop_white"
            board[7][3]="queen_white"
            board[7][4]="king_white"
            board[7][5]="bishop_white"
            board[7][6]="knight_white"
            board[7][7]="rook_white"

            board[1][0]="pawn_black"
            board[1][1]="pawn_black"
            board[1][2]="pawn_black"
            board[1][3]="pawn_black"
            board[1][4]="pawn_black"
            board[1][5]="pawn_black"
            board[1][6]="pawn_black"
            board[1][7]="pawn_black"
            board[0][0]="rook_black"
            board[0][1]="knight_black"
            board[0][2]="bishop_black"
            board[0][3]="queen_black"
            board[0][4]="king_black"
            board[0][5]="bishop_black"
            board[0][6]="knight_black"
            board[0][7]="rook_black"

            player1Image.setImageResource(R.drawable.icon_white)
            player1Username.text=usernameplayer1
            if(time==600000){
                player1Timer.text="10:00"
                player2Timer.text="10:00"
            }
            else if(time==900000){
                player1Timer.text="15:00"
                player2Timer.text="15:00"
            }
            else if(time==1800000){
                player1Timer.text="30:00"
                player2Timer.text="30:00"
            }
            else if(time==300000){
                player1Timer.text="05:00"
                player2Timer.text="05:00"
            }
            else if(time==60000){
                player1Timer.text="01:00"
                player2Timer.text="01:00"
            }
            else if(time==180000){
                player1Timer.text="03:00"
                player2Timer.text="03:00"
            }
            else{
                player1Timer.text="02:00"
                player2Timer.text="02:00"
            }

            player2Image.setImageResource(R.drawable.icon_black)
            player2Username.text=usernameplayer2
        }

        else{

            grid.setBackgroundResource(R.drawable.board_black)

            a2.setImageResource(R.drawable.pawn_black)
            b2.setImageResource(R.drawable.pawn_black)
            c2.setImageResource(R.drawable.pawn_black)
            d2.setImageResource(R.drawable.pawn_black)
            e2.setImageResource(R.drawable.pawn_black)
            f2.setImageResource(R.drawable.pawn_black)
            g2.setImageResource(R.drawable.pawn_black)
            h2.setImageResource(R.drawable.pawn_black)
            a1.setImageResource(R.drawable.rook_black)
            h1.setImageResource(R.drawable.rook_black)
            b1.setImageResource(R.drawable.knight_black)
            g1.setImageResource(R.drawable.knight_black)
            c1.setImageResource(R.drawable.bishop_black)
            f1.setImageResource(R.drawable.bishop_black)
            e1.setImageResource(R.drawable.queen_black)
            d1.setImageResource(R.drawable.king_black)

            a7.setImageResource(R.drawable.pawn_white)
            b7.setImageResource(R.drawable.pawn_white)
            c7.setImageResource(R.drawable.pawn_white)
            d7.setImageResource(R.drawable.pawn_white)
            e7.setImageResource(R.drawable.pawn_white)
            f7.setImageResource(R.drawable.pawn_white)
            g7.setImageResource(R.drawable.pawn_white)
            h7.setImageResource(R.drawable.pawn_white)
            a8.setImageResource(R.drawable.rook_white)
            h8.setImageResource(R.drawable.rook_white)
            b8.setImageResource(R.drawable.knight_white)
            g8.setImageResource(R.drawable.knight_white)
            c8.setImageResource(R.drawable.bishop_white)
            f8.setImageResource(R.drawable.bishop_white)
            e8.setImageResource(R.drawable.queen_white)
            d8.setImageResource(R.drawable.king_white)

            board[6][0]="pawn_black"
            board[6][1]="pawn_black"
            board[6][2]="pawn_black"
            board[6][3]="pawn_black"
            board[6][4]="pawn_black"
            board[6][5]="pawn_black"
            board[6][6]="pawn_black"
            board[6][7]="pawn_black"
            board[7][0]="rook_black"
            board[7][1]="knight_black"
            board[7][2]="bishop_black"
            board[7][3]="king_black"
            board[7][4]="queen_black"
            board[7][5]="bishop_black"
            board[7][6]="knight_black"
            board[7][7]="rook_black"

            board[1][0]="pawn_white"
            board[1][1]="pawn_white"
            board[1][2]="pawn_white"
            board[1][3]="pawn_white"
            board[1][4]="pawn_white"
            board[1][5]="pawn_white"
            board[1][6]="pawn_white"
            board[1][7]="pawn_white"
            board[0][0]="rook_white"
            board[0][1]="knight_white"
            board[0][2]="bishop_white"
            board[0][3]="king_white"
            board[0][4]="queen_white"
            board[0][5]="bishop_white"
            board[0][6]="knight_white"
            board[0][7]="rook_white"

            player1Image.setImageResource(R.drawable.icon_black)
            player1Username.text=usernameplayer1
            if(time==600000){
                player1Timer.text="10:00"
                player2Timer.text="10:00"
            }
            else if(time==900000){
                player1Timer.text="15:00"
                player2Timer.text="15:00"
            }
            else if(time==1800000){
                player1Timer.text="30:00"
                player2Timer.text="30:00"
            }
            else if(time==300000){
                player1Timer.text="05:00"
                player2Timer.text="05:00"
            }
            else if(time==60000){
                player1Timer.text="01:00"
                player2Timer.text="01:00"
            }
            else if(time==180000){
                player1Timer.text="03:00"
                player2Timer.text="03:00"
            }
            else{
                player1Timer.text="02:00"
                player2Timer.text="02:00"
            }

            player2Image.setImageResource(R.drawable.icon_white)
            player2Username.text=usernameplayer2
        }
    }

    fun checkPinned(row:Int,col:Int):Pair<Int,Int>{
        var k_row=0
        var k_col=0
        var king_found=0
        for(i in 0..7){
            for(j in 0..7){
                if(white==player&&board[i][j]=="king_white"){
                    k_row=i
                    k_col=j
                    king_found=1
                    break
                }
                else if(white!=player&&board[i][j]=="king_black"){
                    k_row=i
                    k_col=j
                    king_found=1
                    break
                }
            }
            if(king_found==1)break
        }
        for(i in k_row-1 downTo 0){
            var flg=0
            if(board[i][col]!=""){
                if(i==row){
                    for(j in i-1 downTo 0){
                        if(board[j][col]!=""){
                            if(white==player&&(board[j][col]=="queen_black"||board[j][col]=="rook_black"))return Pair(j,col)
                            else if(white!=player&&(board[j][col]=="queen_white"||board[j][col]=="rook_white"))return Pair(j,col)
                            else{
                                flg=1
                                break
                            }
                        }
                    }
                }
                else break
            }
            if(flg==1)break
        }
        for((i,j) in (k_row-1 downTo 0).zip(k_col-1 downTo 0)){
            var flg=0
            if(board[i][j]!=""){
                if(i==row&&j==col){
                    for((k,l) in (i-1 downTo 0).zip(j-1 downTo 0)){
                        if(board[k][l]!=""){
                            if(white==player&&(board[k][l]=="queen_black"||board[k][l]=="bishop_black"))return Pair(k,l)
                            else if(white!=player&&(board[k][l]=="queen_white"||board[k][l]=="bishop_white"))return Pair(k,l)
                            else{
                                flg=1
                                break
                            }
                        }
                    }
                }
                else break
            }
            if(flg==1)break
        }
        for(i in k_col-1 downTo 0){
            var flg=0
            if(board[row][i]!=""){
                if(i==col){
                    for(j in i-1 downTo 0){
                        if(board[row][j]!=""){
                            if(white==player&&(board[row][j]=="queen_black"||board[row][j]=="rook_black"))return Pair(row,j)
                            else if(white!=player&&(board[row][j]=="queen_white"||board[row][j]=="rook_white"))return Pair(row,j)
                            else{
                                flg=1
                                break
                            }
                        }
                    }
                }
                else break
            }
            if(flg==1)break
        }
        for((i,j) in (k_row+1 .. 7).zip(k_col-1 downTo 0)){
            var flg=0
            if(board[i][j]!=""){
                if(i==row&&j==col){
                    for((k,l) in (i+1 .. 7).zip(j-1 downTo 0)){
                        if(board[k][l]!=""){
                            if(white==player&&(board[k][l]=="queen_black"||board[k][l]=="bishop_black"))return Pair(k,l)
                            else if(white!=player&&(board[k][l]=="queen_white"||board[k][l]=="bishop_white"))return Pair(k,l)
                            else{
                                flg=1
                                break
                            }
                        }
                    }
                }
                else break
            }
            if(flg==1)break
        }
        for(i in k_row+1 ..7){
            var flg=0
            if(board[i][col]!=""){
                if(i==row){
                    for(j in i+1 .. 7){
                        if(board[j][col]!=""){
                            if(white==player&&(board[j][col]=="queen_black"||board[j][col]=="rook_black"))return Pair(j,col)
                            else if(white!=player&&(board[j][col]=="queen_white"||board[j][col]=="rook_white"))return Pair(j,col)
                            else{
                                flg=1
                                break
                            }
                        }
                    }
                }
                else break
            }
            if(flg==1)break
        }
        for((i,j) in (k_row+1 .. 7).zip(k_col+1 .. 7)){
            var flg=0
            if(board[i][j]!=""){
                if(i==row&&j==col){
                    for((k,l) in (i+1 .. 7).zip(j+1 .. 7)){
                        if(board[k][l]!=""){
                            if(white==player&&(board[k][l]=="queen_black"||board[k][l]=="bishop_black"))return Pair(k,l)
                            else if(white!=player&&(board[k][l]=="queen_white"||board[k][l]=="bishop_white"))return Pair(k,l)
                            else{
                                flg=1
                                break
                            }
                        }
                    }
                }
                else break
            }
            if(flg==1)break
        }
        for(i in k_col+1 ..7){
            var flg=0
            if(board[row][i]!=""){
                if(i==col){
                    for(j in i+1 .. 7){
                        if(board[row][j]!=""){
                            if(white==player&&(board[row][j]=="queen_black"||board[row][j]=="rook_black"))return Pair(row,j)
                            else if(white!=player&&(board[row][j]=="queen_white"||board[row][j]=="rook_white"))return Pair(row,j)
                            else{
                                flg=1
                                break
                            }
                        }
                    }
                }
                else break
            }
            if(flg==1)break
        }
        for((i,j) in (k_row-1 downTo 0).zip(k_col+1 .. 7)){
            var flg=0
            if(board[i][j]!=""){
                if(i==row&&j==col){
                    for((k,l) in (i-1 downTo 0).zip(j+1 .. 7)){
                        if(board[k][l]!=""){
                            if(white==player&&(board[k][l]=="queen_black"||board[k][l]=="bishop_black"))return Pair(k,l)
                            else if(white!=player&&(board[k][l]=="queen_white"||board[k][l]=="bishop_white"))return Pair(k,l)
                            else{
                                flg=1
                                break
                            }
                        }
                    }
                }
                else break
            }
            if(flg==1)break
        }
        return Pair(-1,-1)
    }

    fun isCheck():Pair<Int,MutableList<Pair<Int,Int>>>{
        var k_row=0
        var k_col=0
        var king_found=0
        for(i in 0..7){
            for(j in 0..7){
                if(white==player&&board[i][j]=="king_white"){
                    k_row=i
                    k_col=j
                    king_found=1
                    break
                }
                else if(white!=player&&board[i][j]=="king_black"){
                    k_row=i
                    k_col=j
                    king_found=1
                    break
                }
            }
            if(king_found==1)break
        }
        var checkers:MutableList<Pair<Int,Int>> = mutableListOf()
        var north=0
        var northWest=0
        var west=0
        var southWest=0
        var south=0
        var southEast=0
        var east=0
        var northEast=0

        for(i in k_row-1 downTo 0){
            if(board[i][k_col]!=""){
                if(white==player){
                    if(board[i][k_col]=="queen_black"||board[i][k_col]=="rook_black"){
                        checkers.add(Pair(i,k_col))
                        north=1
                    }
                    break
                }
                else{
                    if(board[i][k_col]=="queen_white"||board[i][k_col]=="rook_white"){
                        checkers.add(Pair(i,k_col))
                        north=1
                    }
                    break
                }
            }
        }
        for((i,j) in (k_row-1 downTo 0).zip(k_col-1 downTo 0)){
            if(board[i][j]!=""){
                if(i==k_row-1&&j==k_col-1){
                    if(white==player){
                        if(board[i][j]=="pawn_black"||board[i][j]=="queen_black"||board[i][j]=="bishop_black"){
                            checkers.add(Pair(i,j))
                            northWest=1
                        }
                        break
                    }
                    else{
                        if(board[i][j]=="pawn_white"||board[i][j]=="queen_white"||board[i][j]=="bishop_white"){
                            checkers.add(Pair(i,j))
                            northWest=1
                        }
                        break
                    }
                }
                else{
                    if(white==player){
                        if(board[i][j]=="queen_black"||board[i][j]=="bishop_black"){
                            checkers.add(Pair(i,j))
                            northWest=1
                        }
                        break
                    }
                    else{
                        if(board[i][j]=="queen_white"||board[i][j]=="bishop_white"){
                            checkers.add(Pair(i,j))
                            northWest=1
                        }
                        break
                    }
                }
            }
        }
        for(i in k_col-1 downTo 0){
            if(board[k_row][i]!=""){
                if(white==player){
                    if(board[k_row][i]=="queen_black"||board[k_row][i]=="rook_black"){
                        checkers.add(Pair(k_row,i))
                        west=1
                    }
                    break
                }
                else{
                    if(board[k_row][i]=="queen_white"||board[k_row][i]=="rook_white"){
                        checkers.add(Pair(k_row,i))
                        west=1
                    }
                    break
                }
            }
        }
        for((i,j) in (k_row+1 .. 7).zip(k_col-1 downTo 0)){
            if(board[i][j]!=""){
                if(white==player){
                    if(board[i][j]=="queen_black"||board[i][j]=="bishop_black"){
                        checkers.add(Pair(i,j))
                        southWest=1
                    }
                    break
                }
                else{
                    if(board[i][j]=="queen_white"||board[i][j]=="bishop_white"){
                        checkers.add(Pair(i,j))
                        southWest=1
                    }
                    break
                }
            }
        }
        for(i in k_row+1 .. 7){
            if(board[i][k_col]!=""){
                if(white==player){
                    if(board[i][k_col]=="queen_black"||board[i][k_col]=="rook_black"){
                        checkers.add(Pair(i,k_col))
                        south=1
                    }
                    break
                }
                else{
                    if(board[i][k_col]=="queen_white"||board[i][k_col]=="rook_white"){
                        checkers.add(Pair(i,k_col))
                        south=1
                    }
                    break
                }
            }
        }
        for((i,j) in (k_row+1 .. 7).zip(k_col+1 .. 7)){
            if(board[i][j]!=""){
                if(white==player){
                    if(board[i][j]=="queen_black"||board[i][j]=="bishop_black"){
                        checkers.add(Pair(i,j))
                        southEast=1
                    }
                    break
                }
                else{
                    if(board[i][j]=="queen_white"||board[i][j]=="bishop_white"){
                        checkers.add(Pair(i,j))
                        southEast=1
                    }
                    break
                }
            }
        }
        for(i in k_col+1 .. 7){
            if(board[k_row][i]!=""){
                if(white==player){
                    if(board[k_row][i]=="queen_black"||board[k_row][i]=="rook_black"){
                        checkers.add(Pair(k_row,i))
                        east=1
                    }
                    break
                }
                else{
                    if(board[k_row][i]=="queen_white"||board[k_row][i]=="rook_white"){
                        checkers.add(Pair(k_row,i))
                        east=1
                    }
                    break
                }
            }
        }
        for((i,j) in (k_row-1 downTo 0).zip(k_col+1 .. 7)){
            if(board[i][j]!=""){
                if(i==k_row-1&&j==k_col+1){
                    if(white==player){
                        if(board[i][j]=="pawn_black"||board[i][j]=="queen_black"||board[i][j]=="bishop_black"){
                            checkers.add(Pair(i,j))
                            northEast=1
                        }
                        break
                    }
                    else{
                        if(board[i][j]=="pawn_white"||board[i][j]=="queen_white"||board[i][j]=="bishop_white"){
                            checkers.add(Pair(i,j))
                            northEast=1
                        }
                        break
                    }
                }
                else{
                    if(white==player){
                        if(board[i][j]=="queen_black"||board[i][j]=="bishop_black"){
                            checkers.add(Pair(i,j))
                            northEast=1
                        }
                        break
                    }
                    else{
                        if(board[i][j]=="queen_white"||board[i][j]=="bishop_white"){
                            checkers.add(Pair(i,j))
                            northEast=1
                        }
                        break
                    }
                }
            }
        }
        val row=k_row
        val col=k_col
        if(row-2>=0&&col-1>=0&&white==player&&board[row-2][col-1]=="knight_black"){
            checkers.add(Pair(row-2,col-1))
        }
        else if(row-2>=0&&col-1>=0&&white!=player&&board[row-2][col-1]=="knight_white"){
            checkers.add(Pair(row-2,col-1))
        }

        if(row-1>=0&&col-2>=0&&white==player&&board[row-1][col-2]=="knight_black"){
            checkers.add(Pair(row-1,col-2))
        }
        else if(row-1>=0&&col-2>=0&&white!=player&&board[row-1][col-2]=="knight_white"){
            checkers.add(Pair(row-1,col-2))
        }

        if(row+1<=7&&col-2>=0&&white==player&&board[row+1][col-2]=="knight_black"){
            checkers.add(Pair(row+1,col-2))
        }
        else if(row+1<=7&&col-2>=0&&white!=player&&board[row+1][col-2]=="knight_white"){
            checkers.add(Pair(row+1,col-2))
        }

        if(row+2<=7&&col-1>=0&&white==player&&board[row+2][col-1]=="knight_black"){
            checkers.add(Pair(row+2,col-1))
        }
        else if(row+2<=7&&col-1>=0&&white!=player&&board[row+2][col-1]=="knight_white"){
            checkers.add(Pair(row+2,col-1))
        }

        if(row+2<=7&&col+1<=7&&white==player&&board[row+2][col+1]=="knight_black"){
            checkers.add(Pair(row+2,col+1))
        }
        else if(row+2<=7&&col+1<=7&&white!=player&&board[row+2][col+1]=="knight_white"){
            checkers.add(Pair(row+2,col+1))
        }

        if(row+1<=7&&col+2<=7&&white==player&&board[row+1][col+2]=="knight_black"){
            checkers.add(Pair(row+1,col+2))
        }
        else if(row+1<=7&&col+2<=7&&white!=player&&board[row+1][col+2]=="knight_white"){
            checkers.add(Pair(row+1,col+2))
        }

        if(row-1>=0&&col+2<=7&&white==player&&board[row-1][col+2]=="knight_black"){
            checkers.add(Pair(row-1,col+2))
        }
        else if(row-1>=0&&col+2<=7&&white!=player&&board[row-1][col+2]=="knight_white"){
            checkers.add(Pair(row-1,col+2))
        }

        if(row-2>=0&&col+1<=7&&white==player&&board[row-2][col+1]=="knight_black"){
            checkers.add(Pair(row-2,col+1))
        }
        else if(row-2>=0&&col+1<=7&&white!=player&&board[row-2][col+1]=="knight_white"){
            checkers.add(Pair(row-2,col+1))
        }

        if(checkers.size>1){
            return Pair(2,checkers)
        }
        else if(checkers.size==1){
            if(north==1){
                for(i in k_row-1 downTo 0){
                    checkers.add(Pair(i,k_col))
                    if(board[i][k_col]!="")break
                }
            }
            else if(northWest==1){
                for((i,j) in (k_row-1 downTo 0).zip(k_col-1 downTo 0)){
                    checkers.add(Pair(i,j))
                    if(board[i][j]!="")break
                }
            }
            else if(west==1){
                for(i in k_col-1 downTo 0){
                    checkers.add(Pair(k_row,i))
                    if(board[k_row][i]!="")break
                }
            }
            else if(southWest==1){
                for((i,j) in (k_row+1 .. 7).zip(k_col-1 downTo 0)){
                    checkers.add(Pair(i,j))
                    if(board[i][j]!="")break
                }
            }
            else if(south==1){
                for(i in k_row+1 .. 7){
                    checkers.add(Pair(i,k_col))
                    if(board[i][k_col]!="")break
                }
            }
            else if(southEast==1){
                for((i,j) in (k_row+1 .. 7).zip(k_col+1 .. 7)){
                    checkers.add(Pair(i,j))
                    if(board[i][j]!="")break
                }
            }
            else if(east==1){
                for(i in k_col+1 .. 7){
                    checkers.add(Pair(k_row,i))
                    if(board[k_row][i]!="")break
                }
            }
            else if(northEast==1){
                for((i,j) in (k_row-1 downTo 0).zip(k_col+1 .. 7)){
                    checkers.add(Pair(i,j))
                    if(board[i][j]!="")break
                }
            }
            return Pair(1,checkers)
        }
        else return Pair(0,checkers)
    }

    fun validPositionsPawn(row:Int,col:Int):ArrayList<Pair<Int,Int>>{
        var positions:ArrayList<Pair<Int,Int>> = arrayListOf()
        val pinner=checkPinned(row,col)
        val checkers=isCheck()
        if(pinner!=Pair(-1,-1)){
            if(row-1==pinner.first&&col-1==pinner.second&&checkers.first==0){
                positions.add(Pair(row-1,col-1))
                return positions
            }
            else if(row-1==pinner.first&&col+1==pinner.second&&checkers.first==0){
                positions.add(Pair(row-1,col+1))
                return positions
            }
            else if(row==pinner.first){
                return positions
            }
            else if(col==pinner.second&&checkers.first==0){
                if(row-1>=0&&board[row-1][col]==""){
                    positions.add(Pair(row-1,col))
                    return positions
                }
            }
        }
        else{
            if(checkers.first==1){
                if(row==6&&board[row-2][col]==""&&checkers.second.contains(Pair(row-2,col))){
                    positions.add(Pair(row-2,col))
                    return positions
                }
                else if(row-1>=0&&board[row-1][col]==""&&checkers.second.contains(Pair(row-1,col))){
                    positions.add(Pair(row-1,col))
                    return positions
                }
                else if(row-1>=0&&col-1>=0&&board[row-1][col-1]!=""){
                    if(white==player&&board[row-1][col-1].substringAfter('_')=="black"&&checkers.second.contains(Pair(row-1,col-1))){
                        positions.add(Pair(row-1,col-1))
                        return positions
                    }
                    else if(white!=player&&board[row-1][col-1].substringAfter('_')=="white"&&checkers.second.contains(Pair(row-1,col-1))){
                        positions.add(Pair(row-1,col-1))
                        return positions
                    }
                }
                else if(row-1>=0&&col+1<=7&&board[row-1][col+1]!=""){
                    if(white==player&&board[row-1][col+1].substringAfter('_')=="black"&&checkers.second.contains(Pair(row-1,col+1))){
                        positions.add(Pair(row-1,col+1))
                        return positions
                    }
                    else if(white!=player&&board[row-1][col+1].substringAfter('_')=="white"&&checkers.second.contains(Pair(row-1,col+1))){
                        positions.add(Pair(row-1,col+1))
                        return positions
                    }
                }
            }
            else if(checkers.first>1){
                return positions
            }
            else{
                if(row-1>=0&&board[row-1][col]==""){
                    positions.add(Pair(row-1,col))
                    if(row==6&&board[row-2][col]=="")positions.add(Pair(row-2,col))
                }
                if(row-1>=0&&col-1>=0&&board[row-1][col-1]!=""){
                    if(white==player&&board[row-1][col-1].substringAfter('_')=="black")positions.add(Pair(row-1,col-1))
                    else if(white!=player&&board[row-1][col-1].substringAfter('_')=="white")positions.add(Pair(row-1,col-1))
                }
                if(row-1>=0&&col+1<=7&&board[row-1][col+1]!=""){
                    if(white==player&&board[row-1][col+1].substringAfter('_')=="black")positions.add(Pair(row-1,col+1))
                    else if(white!=player&&board[row-1][col+1].substringAfter('_')=="white")positions.add(Pair(row-1,col+1))
                }
            }
        }
        return positions
    }

    fun validPositionsKnight(row:Int,col:Int):ArrayList<Pair<Int,Int>>{
        var positions:ArrayList<Pair<Int,Int>> = arrayListOf()
        val pinner=checkPinned(row,col)
        if(pinner!=Pair(-1,-1)){
            return positions
        }
        else{
            val checkers=isCheck()
            if(checkers.first>1)return positions
            else if(checkers.first==1){
                if(row-2>=0&&col-1>=0){
                    if(board[row-2][col-1]==""&&checkers.second.contains(Pair(row-2,col-1))){
                        positions.add(Pair(row-2,col-1))
                        return positions
                    }
                    else if(board[row-2][col-1]!=""){
                        if(white==player&&board[row-2][col-1].substringAfter('_')=="black"&&checkers.second.contains(Pair(row-2,col-1))){
                            positions.add(Pair(row-2,col-1))
                            return positions
                        }
                        else if(white!=player&&board[row-2][col-1].substringAfter('_')=="white"&&checkers.second.contains(Pair(row-2,col-1))){
                            positions.add(Pair(row-2,col-1))
                            return positions
                        }
                    }
                }
                if(row-1>=0&&col-2>=0){
                    if(board[row-1][col-2]==""&&checkers.second.contains(Pair(row-1,col-2))){
                        positions.add(Pair(row-1,col-2))
                        return positions
                    }
                    else if(board[row-1][col-2]!=""){
                        if(white==player&&board[row-1][col-2].substringAfter('_')=="black"&&checkers.second.contains(Pair(row-1,col-2))){
                            positions.add(Pair(row-1,col-2))
                            return positions
                        }
                        else if(white!=player&&board[row-1][col-2].substringAfter('_')=="white"&&checkers.second.contains(Pair(row-1,col-2))){
                            positions.add(Pair(row-1,col-2))
                            return positions
                        }
                    }
                }
                if(row+1<=7&&col-2>=0){
                    if(board[row+1][col-2]==""&&checkers.second.contains(Pair(row+1,col-2))){
                        positions.add(Pair(row+1,col-2))
                        return positions
                    }
                    else if(board[row+1][col-2]!=""){
                        if(white==player&&board[row+1][col-2].substringAfter('_')=="black"&&checkers.second.contains(Pair(row+1,col-2))){
                            positions.add(Pair(row+1,col-2))
                            return positions
                        }
                        else if(white!=player&&board[row+1][col-2].substringAfter('_')=="white"&&checkers.second.contains(Pair(row+1,col-2))){
                            positions.add(Pair(row+1,col-2))
                            return positions
                        }
                    }
                }
                if(row+2<=7&&col-1>=0){
                    if(board[row+2][col-1]==""&&checkers.second.contains(Pair(row+2,col-1))){
                        positions.add(Pair(row+2,col-1))
                        return positions
                    }
                    else if(board[row+2][col-1]!=""){
                        if(white==player&&board[row+2][col-1].substringAfter('_')=="black"&&checkers.second.contains(Pair(row+2,col-1))){
                            positions.add(Pair(row+2,col-1))
                            return positions
                        }
                        else if(white!=player&&board[row+2][col-1].substringAfter('_')=="white"&&checkers.second.contains(Pair(row+2,col-1))){
                            positions.add(Pair(row+2,col-1))
                            return positions
                        }
                    }
                }
                if(row+2<=7&&col+1<=7){
                    if(board[row+2][col+1]==""&&checkers.second.contains(Pair(row+2,col+1))){
                        positions.add(Pair(row+2,col+1))
                        return positions
                    }
                    else if(board[row+2][col+1]!=""){
                        if(white==player&&board[row+2][col+1].substringAfter('_')=="black"&&checkers.second.contains(Pair(row+2,col+1))){
                            positions.add(Pair(row+2,col+1))
                            return positions
                        }
                        else if(white!=player&&board[row+2][col+1].substringAfter('_')=="white"&&checkers.second.contains(Pair(row+2,col+1))){
                            positions.add(Pair(row+2,col+1))
                            return positions
                        }
                    }
                }
                if(row+1<=7&&col+2<=7){
                    if(board[row+1][col+2]==""&&checkers.second.contains(Pair(row+1,col+2))){
                        positions.add(Pair(row+1,col+2))
                        return positions
                    }
                    else if(board[row+1][col+2]!=""){
                        if(white==player&&board[row+1][col+2].substringAfter('_')=="black"&&checkers.second.contains(Pair(row+1,col+2))){
                            positions.add(Pair(row+1,col+2))
                            return positions
                        }
                        else if(white!=player&&board[row+1][col+2].substringAfter('_')=="white"&&checkers.second.contains(Pair(row+1,col+2))){
                            positions.add(Pair(row+1,col+2))
                            return positions
                        }
                    }
                }
                if(row-1>=0&&col+2<=7){
                    if(board[row-1][col+2]==""&&checkers.second.contains(Pair(row-1,col+2))){
                        positions.add(Pair(row-1,col+2))
                        return positions
                    }
                    else if(board[row-1][col+2]!=""){
                        if(white==player&&board[row-1][col+2].substringAfter('_')=="black"&&checkers.second.contains(Pair(row-1,col+2))){
                            positions.add(Pair(row-1,col+2))
                            return positions
                        }
                        else if(white!=player&&board[row-1][col+2].substringAfter('_')=="white"&&checkers.second.contains(Pair(row-1,col+2))){
                            positions.add(Pair(row-1,col+2))
                            return positions
                        }
                    }
                }
                if(row-2>=0&&col+1<=7){
                    if(board[row-2][col+1]==""&&checkers.second.contains(Pair(row-2,col+1))){
                        positions.add(Pair(row-2,col+1))
                        return positions
                    }
                    else if(board[row-2][col+1]!=""){
                        if(white==player&&board[row-2][col+1].substringAfter('_')=="black"&&checkers.second.contains(Pair(row-2,col+1))){
                            positions.add(Pair(row-2,col+1))
                            return positions
                        }
                        else if(white!=player&&board[row-2][col+1].substringAfter('_')=="white"&&checkers.second.contains(Pair(row-2,col+1))){
                            positions.add(Pair(row-2,col+1))
                            return positions
                        }
                    }
                }
            }
            else{
                if(row-2>=0&&col-1>=0){
                    if(board[row-2][col-1]=="")positions.add(Pair(row-2,col-1))
                    else if(board[row-2][col-1]!=""){
                        if(white==player&&board[row-2][col-1].substringAfter('_')=="black")positions.add(Pair(row-2,col-1))
                        else if(white!=player&&board[row-2][col-1].substringAfter('_')=="white")positions.add(Pair(row-2,col-1))
                    }
                }
                if(row-1>=0&&col-2>=0){
                    if(board[row-1][col-2]=="")positions.add(Pair(row-1,col-2))
                    else if(board[row-1][col-2]!=""){
                        if(white==player&&board[row-1][col-2].substringAfter('_')=="black")positions.add(Pair(row-1,col-2))
                        else if(white!=player&&board[row-1][col-2].substringAfter('_')=="white")positions.add(Pair(row-1,col-2))
                    }
                }
                if(row+1<=7&&col-2>=0){
                    if(board[row+1][col-2]=="")positions.add(Pair(row+1,col-2))
                    else if(board[row+1][col-2]!=""){
                        if(white==player&&board[row+1][col-2].substringAfter('_')=="black")positions.add(Pair(row+1,col-2))
                        else if(white!=player&&board[row+1][col-2].substringAfter('_')=="white")positions.add(Pair(row+1,col-2))
                    }
                }
                if(row+2<=7&&col-1>=0){
                    if(board[row+2][col-1]=="")positions.add(Pair(row+2,col-1))
                    else if(board[row+2][col-1]!=""){
                        if(white==player&&board[row+2][col-1].substringAfter('_')=="black")positions.add(Pair(row+2,col-1))
                        else if(white!=player&&board[row+2][col-1].substringAfter('_')=="white")positions.add(Pair(row+2,col-1))
                    }
                }
                if(row+2<=7&&col+1<=7){
                    if(board[row+2][col+1]=="")positions.add(Pair(row+2,col+1))
                    else if(board[row+2][col+1]!=""){
                        if(white==player&&board[row+2][col+1].substringAfter('_')=="black")positions.add(Pair(row+2,col+1))
                        else if(white!=player&&board[row+2][col+1].substringAfter('_')=="white")positions.add(Pair(row+2,col+1))
                    }
                }
                if(row+1<=7&&col+2<=7){
                    if(board[row+1][col+2]=="")positions.add(Pair(row+1,col+2))
                    else if(board[row+1][col+2]!=""){
                        if(white==player&&board[row+1][col+2].substringAfter('_')=="black")positions.add(Pair(row+1,col+2))
                        else if(white!=player&&board[row+1][col+2].substringAfter('_')=="white")positions.add(Pair(row+1,col+2))
                    }
                }
                if(row-1>=0&&col+2<=7){
                    if(board[row-1][col+2]=="")positions.add(Pair(row-1,col+2))
                    else if(board[row-1][col+2]!=""){
                        if(white==player&&board[row-1][col+2].substringAfter('_')=="black")positions.add(Pair(row-1,col+2))
                        else if(white!=player&&board[row-1][col+2].substringAfter('_')=="white")positions.add(Pair(row-1,col+2))
                    }
                }
                if(row-2>=0&&col+1<=7){
                    if(board[row-2][col+1]=="")positions.add(Pair(row-2,col+1))
                    else if(board[row-2][col+1]!=""){
                        if(white==player&&board[row-2][col+1].substringAfter('_')=="black")positions.add(Pair(row-2,col+1))
                        else if(white!=player&&board[row-2][col+1].substringAfter('_')=="white")positions.add(Pair(row-2,col+1))
                    }
                }
            }
        }
        return positions
    }

    fun validPositionsRook(row:Int,col:Int):ArrayList<Pair<Int,Int>> {
        var positions:ArrayList<Pair<Int,Int>> = arrayListOf()
        val pinner=checkPinned(row,col)
        val checkers=isCheck()
        if(checkers.first>1)return positions
        else if(pinner!=Pair(-1,-1)&&checkers.first==0){
            val p_r=pinner.first
            val p_c=pinner.second
            if(p_r==row||p_c==col){
                positions.add(Pair(p_r,p_c))
                if(p_c>col) {
                    for (i in p_c-1 downTo 0) {
                        if (i == col) continue
                        else if (board[row][i]!="") break
                        positions.add(Pair(row, i))
                    }
                    return positions
                }
                else if(p_c<col){
                    for(i in p_c+1 .. 7){
                        if(i==col)continue
                        else if(board[row][i]!="")break
                        positions.add(Pair(row,i))
                    }
                    return positions
                }
                else if(p_r>row){
                    for(i in p_r-1 downTo 0){
                        if(i==row)continue
                        else if(board[i][col]!="")break
                        positions.add(Pair(i,col))
                    }
                    return positions
                }
                else if(p_r<row){
                    for(i in p_r+1 .. 7){
                        if(i==row)continue
                        else if(board[i][col]!="")break
                        positions.add(Pair(i,col))
                    }
                    return positions
                }
            }
            else{
                return positions
            }
        }
        else if(pinner!=Pair(-1,-1)&&checkers.first==1)return positions
        else{
            if(checkers.first==1){
                for(i in row-1 downTo 0){
                    if(board[i][col]!=""){
                        if(white==player&&board[i][col].substringAfter('_')=="black"&&checkers.second.contains(Pair(i,col)))positions.add(Pair(i,col))
                        else if(white!=player&&board[i][col].substringAfter('_')=="white"&&checkers.second.contains(Pair(i,col)))positions.add(Pair(i,col))
                        return positions
                    }
                    if(checkers.second.contains(Pair(i,col))){
                        positions.add(Pair(i,col))
                        return positions
                    }
                }
                for(i in col-1 downTo 0){
                    if(board[row][i]!=""){
                        if(white==player&&board[row][i].substringAfter('_')=="black"&&checkers.second.contains(Pair(row,i)))positions.add(Pair(row,i))
                        else if(white!=player&&board[row][i].substringAfter('_')=="white"&&checkers.second.contains(Pair(row,i)))positions.add(Pair(row,i))
                        return positions
                    }
                    if(checkers.second.contains(Pair(row,i))) {
                        positions.add(Pair(row, i))
                        return positions
                    }
                }
                for(i in row+1 .. 7){
                    if(board[i][col]!=""){
                        if(white==player&&board[i][col].substringAfter('_')=="black"&&checkers.second.contains(Pair(i,col)))positions.add(Pair(i,col))
                        else if(white!=player&&board[i][col].substringAfter('_')=="white"&&checkers.second.contains(Pair(i,col)))positions.add(Pair(i,col))
                        return positions
                    }
                    if(checkers.second.contains(Pair(i,col))){
                        positions.add(Pair(i,col))
                        return positions
                    }
                }
                for(i in col+1 .. 7){
                    if(board[row][i]!=""){
                        if(white==player&&board[row][i].substringAfter('_')=="black"&&checkers.second.contains(Pair(row,i)))positions.add(Pair(row,i))
                        else if(white!=player&&board[row][i].substringAfter('_')=="white"&&checkers.second.contains(Pair(row,i)))positions.add(Pair(row,i))
                        return positions
                    }
                    if(checkers.second.contains(Pair(row,i))){
                        positions.add(Pair(row,i))
                        return positions
                    }
                }
            }
            else{
                for(i in row-1 downTo 0){
                    if(board[i][col]!=""){
                        if(white==player&&board[i][col].substringAfter('_')=="black")positions.add(Pair(i,col))
                        else if(white!=player&&board[i][col].substringAfter('_')=="white")positions.add(Pair(i,col))
                        break
                    }
                    positions.add(Pair(i,col))
                }
                for(i in col-1 downTo 0){
                    if(board[row][i]!=""){
                        if(white==player&&board[row][i].substringAfter('_')=="black")positions.add(Pair(row,i))
                        else if(white!=player&&board[row][i].substringAfter('_')=="white")positions.add(Pair(row,i))
                        break
                    }
                    positions.add(Pair(row,i))
                }
                for(i in row+1 .. 7){
                    if(board[i][col]!=""){
                        if(white==player&&board[i][col].substringAfter('_')=="black")positions.add(Pair(i,col))
                        else if(white!=player&&board[i][col].substringAfter('_')=="white")positions.add(Pair(i,col))
                        break
                    }
                    positions.add(Pair(i,col))
                }
                for(i in col+1 .. 7){
                    if(board[row][i]!=""){
                        if(white==player&&board[row][i].substringAfter('_')=="black")positions.add(Pair(row,i))
                        else if(white!=player&&board[row][i].substringAfter('_')=="white")positions.add(Pair(row,i))
                        break
                    }
                    positions.add(Pair(row,i))
                }
            }
        }
        return positions
    }

    fun validPositionsBishop(row:Int,col:Int):ArrayList<Pair<Int,Int>>{
        var positions: ArrayList<Pair<Int, Int>> = arrayListOf()
        val pinner = checkPinned(row, col)
        val checkers=isCheck()
        if(checkers.first>1)return positions
        else if (pinner != Pair(-1, -1)) {
            val p_r = pinner.first
            val p_c = pinner.second
            if(p_r==row||p_c==col){
                return positions
            }
            else if(checkers.first==0){
                if(p_r<row&&p_c>col){
                    for((i,j) in (p_r .. 7).zip(p_c downTo 0)){
                        if(i==row&&j==col)continue
                        else if(board[i][j]!=""&&i!=p_r&&j!=p_c)break
                        positions.add(Pair(i,j))
                    }
                }
                else if(p_r<row&&p_c<col){
                    for((i,j) in (p_r .. 7).zip(p_c .. 7)){
                        if(i==row&&j==col)continue
                        else if(board[i][j]!=""&&i!=p_r&&j!=p_c)break
                        positions.add(Pair(i,j))
                    }
                }
                else if(p_r>row&&p_c<col){
                    for((i,j) in (p_r downTo 0).zip(p_c .. 7)){
                        if(i==row&&j==col)continue
                        else if(board[i][j]!=""&&i!=p_r&&j!=p_c)break
                        positions.add(Pair(i,j))
                    }
                }
                else if(p_r>row&&p_c>col){
                    for((i,j) in (p_r downTo 0).zip(p_c downTo 0)){
                        if(i==row&&j==col)continue
                        else if(board[i][j]!=""&&i!=p_r&&j!=p_c)break
                        positions.add(Pair(i,j))
                    }
                }
                return positions
            }
            else if(checkers.first==1)return positions
        }
        else{
            if(checkers.first==1){
                for((i,j) in (row-1 downTo 0).zip(col+1..7)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        break
                    }
                    if(checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                }
                for((i,j) in (row-1 downTo 0).zip(col-1 downTo 0)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        break
                    }
                    if(checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                }
                for((i,j) in (row+1 .. 7).zip(col-1 downTo 0)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        break
                    }
                    if(checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                }
                for((i,j) in (row+1 .. 7).zip(col+1..7)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        break
                    }
                    if(checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                }
            }
            else{
                for((i,j) in (row-1 downTo 0).zip(col+1..7)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black")positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white")positions.add(Pair(i,j))
                        break
                    }
                    positions.add(Pair(i,j))
                }
                for((i,j) in (row-1 downTo 0).zip(col-1 downTo 0)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black")positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white")positions.add(Pair(i,j))
                        break
                    }
                    positions.add(Pair(i,j))
                }
                for((i,j) in (row+1 .. 7).zip(col-1 downTo 0)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black")positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white")positions.add(Pair(i,j))
                        break
                    }
                    positions.add(Pair(i,j))
                }
                for((i,j) in (row+1 .. 7).zip(col+1..7)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black")positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white")positions.add(Pair(i,j))
                        break
                    }
                    positions.add(Pair(i,j))
                }
            }
        }
        return positions
    }

    fun validPositionsQueen(row:Int,col:Int):ArrayList<Pair<Int,Int>>{
        var positions: ArrayList<Pair<Int, Int>> = arrayListOf()
        val pinner = checkPinned(row, col)
        val checkers=isCheck()
        if(checkers.first>1)return positions
        else if (pinner != Pair(-1, -1)&&checkers.first==0) {
            val p_r = pinner.first
            val p_c = pinner.second
            if (p_r == row || p_c == col) {
                if(p_c>col) {
                    for (i in p_c downTo 0) {
                        if (i == col) continue
                        else if (board[row][i]!="") break
                        positions.add(Pair(row, i))
                    }
                    return positions
                }
                else if(p_c<col){
                    for(i in p_c .. 7){
                        if(i==col)continue
                        else if(board[row][i]!="")break
                        positions.add(Pair(row,i))
                    }
                    return positions
                }
                else if(p_r>row){
                    for(i in p_r downTo 0){
                        if(i==row)continue
                        else if(board[i][col]!="")break
                        positions.add(Pair(i,col))
                    }
                    return positions
                }
                else if(p_r<row){
                    for(i in p_r .. 7){
                        if(i==row)continue
                        else if(board[i][col]!="")break
                        positions.add(Pair(i,col))
                    }
                    return positions
                }
            }
            else{
                if(p_r<row&&p_c>col){
                    for((i,j) in (p_r .. 7).zip(p_c downTo 0)){
                        if(i==row&&j==col)continue
                        else if(board[i][j]!="")break
                        positions.add(Pair(i,j))
                    }
                }
                else if(p_r<row&&p_c<col){
                    for((i,j) in (p_r .. 7).zip(p_c .. 7)){
                        if(i==row&&j==col)continue
                        else if(board[i][j]!="")break
                        positions.add(Pair(i,j))
                    }
                }
                else if(p_r>row&&p_c<col){
                    for((i,j) in (p_r downTo 0).zip(p_c .. 7)){
                        if(i==row&&j==col)continue
                        else if(board[i][j]!="")break
                        positions.add(Pair(i,j))
                    }
                }
                else if(p_r>row&&p_c>col){
                    for((i,j) in (p_r downTo 0).zip(p_c downTo 0)){
                        if(i==row&&j==col)continue
                        else if(board[i][j]!="")break
                        positions.add(Pair(i,j))
                    }
                }
                return positions
            }
        }
        else if(pinner!=Pair(-1,-1)&&checkers.first==1)return positions
        else{
            if(checkers.first==1){
                for(i in row-1 downTo 0){
                    if(board[i][col]!=""){
                        if(white==player&&board[i][col].substringAfter('_')=="black"&&checkers.second.contains(Pair(i,col)))positions.add(Pair(i,col))
                        else if(white!=player&&board[i][col].substringAfter('_')=="white"&&checkers.second.contains(Pair(i,col)))positions.add(Pair(i,col))
                        break
                    }
                    if(checkers.second.contains(Pair(i,col)))positions.add(Pair(i,col))
                }
                for(i in col-1 downTo 0){
                    if(board[row][i]!=""){
                        if(white==player&&board[row][i].substringAfter('_')=="black"&&checkers.second.contains(Pair(row,i)))positions.add(Pair(row,i))
                        else if(white!=player&&board[row][i].substringAfter('_')=="white"&&checkers.second.contains(Pair(row,i)))positions.add(Pair(row,i))
                        break
                    }
                    if(checkers.second.contains(Pair(row,i)))positions.add(Pair(row,i))
                }
                for(i in row+1 .. 7){
                    if(board[i][col]!=""){
                        if(white==player&&board[i][col].substringAfter('_')=="black"&&checkers.second.contains(Pair(i,col)))positions.add(Pair(i,col))
                        else if(white!=player&&board[i][col].substringAfter('_')=="white"&&checkers.second.contains(Pair(i,col)))positions.add(Pair(i,col))
                        break
                    }
                    if(checkers.second.contains(Pair(i,col)))positions.add(Pair(i,col))
                }
                for(i in col+1 .. 7){
                    if(board[row][i]!=""){
                        if(white==player&&board[row][i].substringAfter('_')=="black"&&checkers.second.contains(Pair(row,i)))positions.add(Pair(row,i))
                        else if(white!=player&&board[row][i].substringAfter('_')=="white"&&checkers.second.contains(Pair(row,i)))positions.add(Pair(row,i))
                        break
                    }
                    if(checkers.second.contains(Pair(row,i)))positions.add(Pair(row,i))
                }
                for((i,j) in (row-1 downTo 0).zip(col+1..7)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        break
                    }
                    if(checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                }
                for((i,j) in (row-1 downTo 0).zip(col-1 downTo 0)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        break
                    }
                    if(checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                }
                for((i,j) in (row+1 .. 7).zip(col-1 downTo 0)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        break
                    }
                    if(checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                }
                for((i,j) in (row+1 .. 7).zip(col+1..7)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white"&&checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                        break
                    }
                    if(checkers.second.contains(Pair(i,j)))positions.add(Pair(i,j))
                }
            }
            else{
                for(i in row-1 downTo 0){
                    if(board[i][col]!=""){
                        if(white==player&&board[i][col].substringAfter('_')=="black")positions.add(Pair(i,col))
                        else if(white!=player&&board[i][col].substringAfter('_')=="white")positions.add(Pair(i,col))
                        break
                    }
                    positions.add(Pair(i,col))
                }
                for(i in col-1 downTo 0){
                    if(board[row][i]!=""){
                        if(white==player&&board[row][i].substringAfter('_')=="black")positions.add(Pair(row,i))
                        else if(white!=player&&board[row][i].substringAfter('_')=="white")positions.add(Pair(row,i))
                        break
                    }
                    positions.add(Pair(row,i))
                }
                for(i in row+1 .. 7){
                    if(board[i][col]!=""){
                        if(white==player&&board[i][col].substringAfter('_')=="black")positions.add(Pair(i,col))
                        else if(white!=player&&board[i][col].substringAfter('_')=="white")positions.add(Pair(i,col))
                        break
                    }
                    positions.add(Pair(i,col))
                }
                for(i in col+1 .. 7){
                    if(board[row][i]!=""){
                        if(white==player&&board[row][i].substringAfter('_')=="black")positions.add(Pair(row,i))
                        else if(white!=player&&board[row][i].substringAfter('_')=="white")positions.add(Pair(row,i))
                        break
                    }
                    positions.add(Pair(row,i))
                }
                for((i,j) in (row-1 downTo 0).zip(col+1..7)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black")positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white")positions.add(Pair(i,j))
                        break
                    }
                    positions.add(Pair(i,j))
                }
                for((i,j) in (row-1 downTo 0).zip(col-1 downTo 0)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black")positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white")positions.add(Pair(i,j))
                        break
                    }
                    positions.add(Pair(i,j))
                }
                for((i,j) in (row+1 .. 7).zip(col-1 downTo 0)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black")positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white")positions.add(Pair(i,j))
                        break
                    }
                    positions.add(Pair(i,j))
                }
                for((i,j) in (row+1 .. 7).zip(col+1..7)){
                    if(board[i][j]!=""){
                        if(white==player&&board[i][j].substringAfter('_')=="black")positions.add(Pair(i,j))
                        else if(white!=player&&board[i][j].substringAfter('_')=="white")positions.add(Pair(i,j))
                        break
                    }
                    positions.add(Pair(i,j))
                }
            }
        }
        return positions
    }

    fun validDiagonals(row:Int,col:Int):Boolean{
        for((i,j) in (row-1 downTo 0).zip(col+1..7)){
            if(board[i][j]!=""){
                if(white==player&&board[i][j]=="king_white")continue
                else if(white!=player&&board[i][j]=="king_black")continue
                else if(i==row-1&&j==col+1){
                    if(white==player&&(board[i][j]=="pawn_black"||board[i][j]=="bishop_black"||board[i][j]=="queen_black"||board[i][j]=="king_black"))return false
                    else if(white!=player&&(board[i][j]=="pawn_white"||board[i][j]=="bishop_white"||board[i][j]=="queen_white"||board[i][j]=="king_white"))return false
                }
                else{
                    if(white==player&&(board[i][j]=="bishop_black"||board[i][j]=="queen_black"))return false
                    else if(white!=player&&(board[i][j]=="bishop_white"||board[i][j]=="queen_white"))return false
                }
                break
            }
        }
        for((i,j) in (row-1 downTo 0).zip(col-1 downTo 0)){
            if(board[i][j]!=""){
                if(white==player&&board[i][j]=="king_white")continue
                else if(white!=player&&board[i][j]=="king_black")continue
                else if(i==row-1&&j==col-1){
                    if(white==player&&(board[i][j]=="pawn_black"||board[i][j]=="bishop_black"||board[i][j]=="queen_black"||board[i][j]=="king_black"))return false
                    else if(white!=player&&(board[i][j]=="pawn_white"||board[i][j]=="bishop_white"||board[i][j]=="queen_white"||board[i][j]=="king_white"))return false
                }
                else{
                    if(white==player&&(board[i][j]=="bishop_black"||board[i][j]=="queen_black"))return false
                    else if(white!=player&&(board[i][j]=="bishop_white"||board[i][j]=="queen_white"))return false
                }
                break
            }
        }
        for((i,j) in (row+1 .. 7).zip(col-1 downTo 0)){
            if(board[i][j]!="") {
                if(white==player&&board[i][j]=="king_white")continue
                else if(white!=player&&board[i][j]=="king_black")continue
                else if(i==row+1&&j==col-1){
                    if(white==player&&(board[i][j]=="pawn_black"||board[i][j]=="bishop_black"||board[i][j]=="queen_black"||board[i][j]=="king_black"))return false
                    else if(white!=player&&(board[i][j]=="pawn_white"||board[i][j]=="bishop_white"||board[i][j]=="queen_white"||board[i][j]=="king_white"))return false
                }
                else{
                    if(white==player&&(board[i][j]=="bishop_black"||board[i][j]=="queen_black"))return false
                    else if(white!=player&&(board[i][j]=="bishop_white"||board[i][j]=="queen_white"))return false
                }
                break
            }
        }
        for((i,j) in (row+1 .. 7).zip(col+1..7)){
            if(board[i][j]!="") {
                if(white==player&&board[i][j]=="king_white")continue
                else if(white!=player&&board[i][j]=="king_black")continue
                else if(i==row+1&&j==col+1){
                    if(white==player&&(board[i][j]=="pawn_black"||board[i][j]=="bishop_black"||board[i][j]=="queen_black"||board[i][j]=="king_black"))return false
                    else if(white!=player&&(board[i][j]=="pawn_white"||board[i][j]=="bishop_white"||board[i][j]=="queen_white"||board[i][j]=="king_white"))return false
                }
                else{
                    if(white==player&&(board[i][j]=="bishop_black"||board[i][j]=="queen_black"))return false
                    else if(white!=player&&(board[i][j]=="bishop_white"||board[i][j]=="queen_white"))return false
                }
                break
            }
        }
        return true
    }

    fun validAccross(row:Int,col:Int):Boolean{
        for(i in row-1 downTo 0){
            if(board[i][col]!=""){
                if(white==player&&board[i][col]=="king_white")continue
                else if(white!=player&&board[i][col]=="king_black")continue
                else if(i==row-1){
                    if(white==player&&(board[i][col]=="king_black"||board[i][col]=="queen_black"||board[i][col]=="rook_black"))return false
                    else if(white!=player&&(board[i][col]=="king_white"||board[i][col]=="queen_white"||board[i][col]=="rook_white"))return false
                }
                else{
                    if(white==player&&(board[i][col]=="queen_black"||board[i][col]=="rook_black"))return false
                    else if(white!=player&&(board[i][col]=="queen_white"||board[i][col]=="rook_white"))return false
                }
                break
            }
        }
        for(i in col-1 downTo 0){
            if(board[row][i]!=""){
                if(white==player&&board[row][i]=="king_white")continue
                else if(white!=player&&board[row][i]=="king_black")continue
                else if(i==col-1){
                    if(white==player&&(board[row][i]=="king_black"||board[row][i]=="queen_black"||board[row][i]=="rook_black"))return false
                    else if(white!=player&&(board[row][i]=="king_white"||board[row][i]=="queen_white"||board[row][i]=="rook_white"))return false
                }
                else{
                    if(white==player&&(board[row][i]=="queen_black"||board[row][i]=="rook_black"))return false
                    else if(white!=player&&(board[row][i]=="queen_white"||board[row][i]=="rook_white"))return false
                }
                break
            }
        }
        for(i in row+1 .. 7){
            if(board[i][col]!=""){
                if(white==player&&board[i][col]=="king_white")continue
                else if(white!=player&&board[i][col]=="king_black")continue
                else if(i==row+1){
                    if(white==player&&(board[i][col]=="king_black"||board[i][col]=="queen_black"||board[i][col]=="rook_black"))return false
                    else if(white!=player&&(board[i][col]=="king_white"||board[i][col]=="queen_white"||board[i][col]=="rook_white"))return false
                }
                else {
                    if (white == player && (board[i][col] == "queen_black" || board[i][col] == "rook_black")) return false
                    else if (white != player && (board[i][col] == "queen_white" || board[i][col] == "rook_white")) return false
                }
                break
            }
        }
        for(i in col+1..7){
            if(board[row][i]!=""){
                if(white==player&&board[row][i]=="king_white")continue
                else if(white!=player&&board[row][i]=="king_black")continue
                else if(i==col+1){
                    if(white==player&&(board[row][i]=="king_black"||board[row][i]=="queen_black"||board[row][i]=="rook_black"))return false
                    else if(white!=player&&(board[row][i]=="king_white"||board[row][i]=="queen_white"||board[row][i]=="rook_white"))return false
                }
                else {
                    if (white == player && (board[row][i] == "queen_black" || board[row][i] == "rook_black")) return false
                    else if (white != player && (board[row][i] == "queen_white" || board[row][i] == "rook_white")) return false
                }
                break
            }
        }
        return true
    }

    fun validL(row:Int,col:Int):Boolean{
        if(row-2>=0&&col-1>=0&&white==player&&board[row-2][col-1]=="knight_black")return false
        else if(row-2>=0&&col-1>=0&&white!=player&&board[row-2][col-1]=="knight_white")return false

        if(row-1>=0&&col-2>=0&&white==player&&board[row-1][col-2]=="knight_black")return false
        else if(row-1>=0&&col-2>=0&&white!=player&&board[row-1][col-2]=="knight_white")return false

        if(row+1<=7&&col-2>=0&&white==player&&board[row+1][col-2]=="knight_black")return false
        else if(row+1<=7&&col-2>=0&&white!=player&&board[row+1][col-2]=="knight_white")return false

        if(row+2<=7&&col-1>=0&&white==player&&board[row+2][col-1]=="knight_black")return false
        else if(row+2<=7&&col-1>=0&&white!=player&&board[row+2][col-1]=="knight_white")return false

        if(row+2<=7&&col+1<=7&&white==player&&board[row+2][col+1]=="knight_black")return false
        else if(row+2<=7&&col+1<=7&&white!=player&&board[row+2][col+1]=="knight_white")return false

        if(row+1<=7&&col+2<=7&&white==player&&board[row+1][col+2]=="knight_black")return false
        else if(row+1<=7&&col+2<=7&&white!=player&&board[row+1][col+2]=="knight_white")return false

        if(row-1>=0&&col+2<=7&&white==player&&board[row-1][col+2]=="knight_black")return false
        else if(row-1>=0&&col+2<=7&&white!=player&&board[row-1][col+2]=="knight_white")return false

        if(row-2>=0&&col+1<=7&&white==player&&board[row-2][col+1]=="knight_black")return false
        else if(row-2>=0&&col+1<=7&&white!=player&&board[row-2][col+1]=="knight_white")return false
        return true
    }

    fun leftCastleAvailableWhite():Boolean{
        if(king_moved==1||left_rook_moved==1)return false
        if(board[7][1]!=""||board[7][2]!=""||board[7][3]!="")return false
        return validL(7,2)&&validL(7,3)&&validDiagonals(7,3)&&validDiagonals(7,2)&&validAccross(7,2)&&validAccross(7,3)
    }

    fun rightCastleAvailableWhite():Boolean{
        if(king_moved==1||right_rook_moved==1)return false
        if(board[7][5]!=""||board[7][6]!="")return false
        return validL(7,5)&&validL(7,6)&&validDiagonals(7,5)&&validDiagonals(7,6)&&validAccross(7,5)&&validAccross(7,6)
    }

    fun leftCastleAvailableBlack():Boolean{
        if(king_moved==1||left_rook_moved==1)return false
        if(board[7][1]!=""||board[7][2]!="")return false
        return validL(7,2)&&validL(7,1)&&validDiagonals(7,1)&&validDiagonals(7,2)&&validAccross(7,2)&&validAccross(7,1)
    }

    fun rightCastleAvailableBlack():Boolean{
        if(king_moved==1||right_rook_moved==1)return false
        if(board[7][4]!=""||board[7][5]!=""||board[7][6]!="")return false
        return validL(7,5)&&validL(7,4)&&validDiagonals(7,4)&&validDiagonals(7,5)&&validAccross(7,5)&&validAccross(7,4)
    }

    fun validPositionsKing(row:Int,col:Int):ArrayList<Pair<Int,Int>>{
        var positions:ArrayList<Pair<Int,Int>> = arrayListOf()
        if(row-1>=0){
            if(white==player&&(board[row-1][col].substringAfter('_')=="black"||board[row-1][col]=="")){
                if(validDiagonals(row-1,col)&&validAccross(row - 1,col)&&validL(row-1,col)) {
                    positions.add(Pair(row-1,col))
                }
            }
            else if(white!=player&&(board[row-1][col].substringAfter('_')=="white"||board[row-1][col]=="")){
                if(validDiagonals(row-1,col)&&validAccross(row - 1,col)&&validL(row-1,col)) {
                    positions.add(Pair(row-1,col))
                }
            }
        }
        if(row-1>=0&&col-1>=0){
            if(white==player&&(board[row-1][col-1].substringAfter('_')=="black"||board[row-1][col-1]=="")){
                if(validDiagonals(row-1,col-1)&&validAccross(row - 1,col-1)&&validL(row - 1,col-1)) {
                    positions.add(Pair(row-1,col-1))
                }
            }
            else if(white!=player&&(board[row-1][col-1].substringAfter('_')=="white"||board[row-1][col-1]=="")){
                if(validDiagonals(row-1,col-1)&&validAccross(row - 1,col-1)&&validL(row - 1,col-1)) {
                    positions.add(Pair(row-1,col-1))
                }
            }
        }
        if(col-1>=0){
            if(white==player&&(board[row][col-1].substringAfter('_')=="black"||board[row][col-1]=="")){
                if(validDiagonals(row,col-1)&&validAccross(row,col-1)&&validL(row,col-1)) {
                    positions.add(Pair(row,col-1))
                }
            }
            else if(white!=player&&(board[row][col-1].substringAfter('_')=="white"||board[row][col-1]=="")){
                if(validDiagonals(row,col-1)&&validAccross(row,col-1)&&validL(row,col-1)) {
                    positions.add(Pair(row,col-1))
                }
            }
        }
        if(row+1<=7&&col-1>=0){
            if(white==player&&(board[row+1][col-1].substringAfter('_')=="black"||board[row+1][col-1]=="")){
                if(validDiagonals(row+1,col-1)&&validAccross(row + 1,col-1)&&validL(row + 1,col-1)) {
                    positions.add(Pair(row+1,col-1))
                }
            }
            else if(white!=player&&(board[row+1][col-1].substringAfter('_')=="white"||board[row+1][col-1]=="")){
                if(validDiagonals(row+1,col-1)&&validAccross(row + 1,col-1)&&validL(row + 1,col-1)) {
                    positions.add(Pair(row+1,col-1))
                }
            }
        }
        if(row+1<=7){
            if(white==player&&(board[row+1][col].substringAfter('_')=="black"||board[row+1][col]=="")){
                if(validDiagonals(row+1,col)&&validAccross(row + 1,col)&&validL(row + 1,col)) {
                    positions.add(Pair(row+1,col))
                }
            }
            else if(white!=player&&(board[row+1][col].substringAfter('_')=="white"||board[row+1][col]=="")){
                if(validDiagonals(row+1,col)&&validAccross(row + 1,col)&&validL(row + 1,col)) {
                    positions.add(Pair(row+1,col))
                }
            }
        }
        if(row+1<=7&&col+1<=7){
            if(white==player&&(board[row+1][col+1].substringAfter('_')=="black"||board[row+1][col+1]=="")){
                if(validDiagonals(row+1,col+1)&&validAccross(row + 1,col+1)&&validL(row + 1,col+1)) {
                    positions.add(Pair(row+1,col+1))
                }
            }
            else if(white!=player&&(board[row+1][col+1].substringAfter('_')=="white"||board[row+1][col+1]=="")){
                if(validDiagonals(row+1,col+1)&&validAccross(row + 1,col+1)&&validL(row + 1,col+1)) {
                    positions.add(Pair(row+1,col+1))
                }
            }
        }
        if(col+1<=7){
            if(white==player&&(board[row][col+1].substringAfter('_')=="black"||board[row][col+1]=="")){
                if(validDiagonals(row,col+1)&&validAccross(row,col+1)&&validL(row,col+1)) {
                    positions.add(Pair(row,col+1))
                }
            }
            else if(white!=player&&(board[row][col+1].substringAfter('_')=="white"||board[row][col+1]=="")){
                if(validDiagonals(row,col+1)&&validAccross(row,col+1)&&validL(row,col+1)) {
                    positions.add(Pair(row,col+1))
                }
            }
        }
        if(row-1>=0&&col+1<=7){
            if(white==player&&(board[row-1][col+1].substringAfter('_')=="black"||board[row-1][col+1]=="")){
                if(validDiagonals(row-1,col+1)&&validAccross(row - 1,col+1)&&validL(row - 1,col+1)) {
                    positions.add(Pair(row-1,col+1))
                }
            }
            else if(white!=player&&(board[row-1][col+1].substringAfter('_')=="white"||board[row-1][col+1]=="")){
                if(validDiagonals(row-1,col+1)&&validAccross(row - 1,col+1)&&validL(row - 1,col+1)) {
                    positions.add(Pair(row-1,col+1))
                }
            }
        }
        val checkers=isCheck()
        if(white==player&&board[7][4]=="king_white"&&checkers.first==0){
            if(leftCastleAvailableWhite()){
                positions.add(Pair(7,2))
                positions.add(Pair(-2,-1))
            }
            if(rightCastleAvailableWhite()){
                positions.add(Pair(7,6))
                positions.add(Pair(-1,-1))
            }
        }
        else if(white!=player&&board[7][3]=="king_black"&&checkers.first==0){
            if(leftCastleAvailableBlack()){
                positions.add(Pair(7,1))
                positions.add(Pair(-2,-2))
            }
            if(rightCastleAvailableBlack()){
                positions.add(Pair(7,5))
                positions.add(Pair(-1,-2))
            }
        }
        return positions
    }

    fun validPositions(row:Int,col:Int,piece:String,): ArrayList<Pair<Int,Int>>{
        if(piece=="pawn")return validPositionsPawn(row,col)
        else if(piece=="rook")return validPositionsRook(row,col)
        else if(piece=="knight")return validPositionsKnight(row,col)
        else if(piece=="bishop")return validPositionsBishop(row,col)
        else if(piece=="king")return validPositionsKing(row,col)
        return validPositionsQueen(row,col)
    }

    fun isCheckmate():Boolean{
        for(i in 0..7){
            for(j in 0..7){
                if(white==player&&board[i][j]!=""&&board[i][j].substringAfter('_')=="white"){
                    if(validPositions(i,j,board[i][j].substringBefore('_')).isNotEmpty())return false
                }
                else if(white!=player&&board[i][j]!=""&&board[i][j].substringAfter('_')=="black"){
                    if(validPositions(i,j,board[i][j].substringBefore('_')).isNotEmpty())return false
                }
            }
        }
        return true
    }

    fun displayPositions(positions: ArrayList<Pair<Int,Int>>,image_view:HashMap<Pair<Int,Int>,ImageView>){
        for(pair in positions){
            image_view[pair]?.setImageDrawable(LayerDrawable(arrayOf(image_view[pair]?.drawable,ContextCompat.getDrawable(this,R.drawable.square))))
        }
    }

    fun removePositions(positions: ArrayList<Pair<Int,Int>>,image_view:HashMap<Pair<Int,Int>,ImageView>){
        for(pair in positions){
            if(board[pair.first][pair.second]=="pawn_white")image_view[pair]?.setImageResource(R.drawable.pawn_white)
            else if(board[pair.first][pair.second]=="pawn_black")image_view[pair]?.setImageResource(R.drawable.pawn_black)
            else if(board[pair.first][pair.second]=="king_white")image_view[pair]?.setImageResource(R.drawable.king_white)
            else if(board[pair.first][pair.second]=="king_black")image_view[pair]?.setImageResource(R.drawable.king_black)
            else if(board[pair.first][pair.second]=="queen_white")image_view[pair]?.setImageResource(R.drawable.queen_white)
            else if(board[pair.first][pair.second]=="queen_black")image_view[pair]?.setImageResource(R.drawable.queen_black)
            else if(board[pair.first][pair.second]=="rook_white")image_view[pair]?.setImageResource(R.drawable.rook_white)
            else if(board[pair.first][pair.second]=="rook_black")image_view[pair]?.setImageResource(R.drawable.rook_black)
            else if(board[pair.first][pair.second]=="bishop_white")image_view[pair]?.setImageResource(R.drawable.bishop_white)
            else if(board[pair.first][pair.second]=="bishop_black")image_view[pair]?.setImageResource(R.drawable.bishop_black)
            else if(board[pair.first][pair.second]=="knight_white")image_view[pair]?.setImageResource(R.drawable.knight_white)
            else if(board[pair.first][pair.second]=="knight_black")image_view[pair]?.setImageResource(R.drawable.knight_black)
            else image_view[pair]?.setImageResource(0)
        }
    }

    fun validClick(row:Int, col:Int):Boolean{
        val piece=board[row][col]
        if(piece!=""){
            if(white==player){
                if(piece[piece.lastIndex]=='e')return true
            }
            else{
                if(piece[piece.lastIndex]=='k')return true
            }
        }
        return false
    }

    fun removeOnClickListeners(image_view:HashMap<Pair<Int,Int>,ImageView>){
        for(p in image_view.keys){
            image_view[p]?.setOnClickListener(null)
        }
    }

    fun reflectOppMove(image_view:HashMap<Pair<Int,Int>,ImageView>,document:HashMap<*,*>){
        Log.d("mytag","hi")
        var fromPos= document["fromPos"] as MutableList<Int>
        var toPos= document["toPos"] as MutableList<Int>
        var promotedTo= document["promotedTo"] as String
        if(fromPos.isNotEmpty()&&toPos.isNotEmpty()){
            if(board[fromPos[0]][fromPos[1]]=="")return
            updatePointsOpp(toPos[0],toPos[1])
            if(toPos[0]==7&&fromPos[0]==6&&board[fromPos[0]][fromPos[1]].substringBefore('_')=="pawn"){
                board[fromPos[0]][fromPos[1]]=""
                if(promotedTo=="queen"&&white==player)board[toPos[0]][toPos[1]]="queen_black"
                else if(promotedTo=="queen"&&white!=player)board[toPos[0]][toPos[1]]="queen_white"
                else if(promotedTo=="rook"&&white!=player)board[toPos[0]][toPos[1]]="rook_white"
                else if(promotedTo=="rook"&&white==player)board[toPos[0]][toPos[1]]="rook_black"
                else if(promotedTo=="bishop"&&white!=player)board[toPos[0]][toPos[1]]="bishop_white"
                else if(promotedTo=="bishop"&&white==player)board[toPos[0]][toPos[1]]="bishop_black"
                else if(promotedTo=="knight"&&white!=player)board[toPos[0]][toPos[1]]="knight_white"
                else if(promotedTo=="knight"&&white==player)board[toPos[0]][toPos[1]]="knight_black"
            }
            else{
                board[toPos[0]][toPos[1]]=board[fromPos[0]][fromPos[1]]
                board[fromPos[0]][fromPos[1]]=""
            }
            if(fromPos.size>2){
                board[toPos[2]][toPos[3]]=board[fromPos[2]][fromPos[3]]
                board[fromPos[2]][fromPos[3]]=""
            }
            for(i in 0..7){
                for(j in 0..7){
                    if(board[i][j]=="pawn_white")image_view[Pair(i,j)]?.setImageResource(R.drawable.pawn_white)
                    else if(board[i][j]=="pawn_black")image_view[Pair(i,j)]?.setImageResource(R.drawable.pawn_black)
                    else if(board[i][j]=="king_black")image_view[Pair(i,j)]?.setImageResource(R.drawable.king_black)
                    else if(board[i][j]=="king_white")image_view[Pair(i,j)]?.setImageResource(R.drawable.king_white)
                    else if(board[i][j]=="queen_black")image_view[Pair(i,j)]?.setImageResource(R.drawable.queen_black)
                    else if(board[i][j]=="queen_white")image_view[Pair(i,j)]?.setImageResource(R.drawable.queen_white)
                    else if(board[i][j]=="rook_black")image_view[Pair(i,j)]?.setImageResource(R.drawable.rook_black)
                    else if(board[i][j]=="rook_white")image_view[Pair(i,j)]?.setImageResource(R.drawable.rook_white)
                    else if(board[i][j]=="bishop_black")image_view[Pair(i,j)]?.setImageResource(R.drawable.bishop_black)
                    else if(board[i][j]=="bishop_white")image_view[Pair(i,j)]?.setImageResource(R.drawable.bishop_white)
                    else if(board[i][j]=="knight_black")image_view[Pair(i,j)]?.setImageResource(R.drawable.knight_black)
                    else if(board[i][j]=="knight_white")image_view[Pair(i,j)]?.setImageResource(R.drawable.knight_white)
                    else image_view[Pair(i,j)]?.setImageResource(0)
                }
            }
        }
    }

    fun reflectMyMove(fromPos: Pair<Int, Int>, toPos: Pair<Int,Int>,hasPromoted:Pair<Int,String> = Pair(0,""),fromPos1: Pair<Int,Int> = Pair(-1,-1), toPos1: Pair<Int,Int> = Pair(-1,-1)){
        var newFromPos:MutableList<Int> = mutableListOf(7-fromPos.first,7-fromPos.second)
        var newToPos:MutableList<Int> = mutableListOf(7-toPos.first,7-toPos.second)
        if(fromPos1!=Pair(-1,-1)&&toPos1!=Pair(-1,-1)) {
            newFromPos.add(7-fromPos1.first)
            newFromPos.add(7-fromPos1.second)
            newToPos.add(7-toPos1.first)
            newToPos.add(7-toPos1.second)
        }
        var gameDocumentRef = game_id.let { it1 -> firestore.collection("games").document(it1) }
        var newTurn=""
        if(turn=="player1")newTurn="player2"
        else newTurn="player1"
        pauseTimer()
        if(hasPromoted.first==1){
            val updates= hashMapOf(
                "turn" to newTurn,
                "fromPos" to newFromPos,
                "toPos" to newToPos,
                "promotedTo" to hasPromoted.second,
                "timer1" to timeLeftInMillis,
                "timer2" to timeLeftInMillisOpp
            )
            gameDocumentRef.update(updates)
        }
        else {
            val updates = hashMapOf(
                "turn" to newTurn,
                "fromPos" to newFromPos,
                "toPos" to newToPos,
                "timer1" to timeLeftInMillis,
                "timer2" to timeLeftInMillisOpp
            )
            gameDocumentRef.update(updates)
        }
    }

    fun showPromotionOptions(fromPos: Pair<Int, Int>, toPos: Pair<Int, Int>, image_view:HashMap<Pair<Int,Int>,ImageView>){
        val row=toPos.first
        val col=toPos.second
        if(white==player){
            val items= listOf(
                promotionsListItem(R.drawable.queen_white, "Queen"),
                promotionsListItem(R.drawable.rook_white, "Rook"),
                promotionsListItem(R.drawable.bishop_white, "Bishop"),
                promotionsListItem(R.drawable.knight_white, "Knight")
            )
            val adapter=ItemAdapter(this,items)
            val builder=AlertDialog.Builder(this).create()
            val listView=ListView(this)
            listView.adapter=adapter
            listView.setOnItemClickListener { _, _, position, _ ->
                val selectedItem=items[position]
                image_view[Pair(row,col)]?.setImageResource(selectedItem.imageResId)
                if(selectedItem.piece=="Queen"){
                    board[row][col]="queen_white"
                    reflectMyMove(fromPos,toPos,Pair(1,"queen"))
                }
                if(selectedItem.piece=="Rook"){
                    board[row][col]="rook_white"
                    reflectMyMove(fromPos,toPos,Pair(1,"rook"))
                }
                if(selectedItem.piece=="Bishop"){
                    board[row][col]="bishop_white"
                    reflectMyMove(fromPos,toPos,Pair(1,"bishop"))
                }
                if(selectedItem.piece=="Knight"){
                    board[row][col]="knight_white"
                    reflectMyMove(fromPos,toPos,Pair(1,"knight"))
                }
                builder.dismiss()
            }
            builder.setView(listView)
            builder.show()
        }
        else{
            val items= listOf(
                promotionsListItem(R.drawable.queen_black, "Queen"),
                promotionsListItem(R.drawable.rook_black, "Rook"),
                promotionsListItem(R.drawable.bishop_black, "Bishop"),
                promotionsListItem(R.drawable.knight_black, "Knight")
            )
            val adapter=ItemAdapter(this,items)
            val builder=AlertDialog.Builder(this).create()
            val listView=ListView(this)
            listView.adapter=adapter
            listView.setOnItemClickListener { _, _, position, _ ->
                val selectedItem=items[position]
                image_view[Pair(row,col)]?.setImageResource(selectedItem.imageResId)
                if(selectedItem.piece=="Queen"){
                    board[row][col]="queen_black"
                    reflectMyMove(fromPos,toPos,Pair(1,"queen"))
                }
                if(selectedItem.piece=="Rook"){
                    board[row][col]="rook_black"
                    reflectMyMove(fromPos,toPos,Pair(1,"rook"))
                }
                if(selectedItem.piece=="Bishop"){
                    board[row][col]="bishop_black"
                    reflectMyMove(fromPos,toPos,Pair(1,"bishop"))
                }
                if(selectedItem.piece=="knight"){
                    board[row][col]="knight_black"
                    reflectMyMove(fromPos,toPos,Pair(1,"knight"))
                }
                builder.dismiss()
            }
            builder.setView(listView)
            builder.show()
        }
    }

    fun normalizePoints(){
        if(points>pointsOpp){
            points-=pointsOpp
            pointsOpp=0
        }
        else if(pointsOpp>points){
            pointsOpp-=points
            points=0
        }
        else{
            points=0
            pointsOpp=0
        }

        val pointsText1=findViewById<TextView>(R.id.player1Points)
        val pointsText2=findViewById<TextView>(R.id.player2Points)
        if(points>0){
            pointsText1.visibility=View.VISIBLE
            pointsText1.setText("+$points")
        }
        else{
            pointsText1.visibility=View.INVISIBLE
        }
        if(pointsOpp>0){
            pointsText2.visibility=View.VISIBLE
            pointsText2.setText("+$pointsOpp")
        }
        else{
            pointsText2.visibility=View.INVISIBLE
        }
    }

    fun updatePoints(row: Int,col: Int){
        if(board[row][col].substringBefore('_')=="pawn")points+=1
        else if(board[row][col].substringBefore('_')=="knight")points+=3
        else if(board[row][col].substringBefore('_')=="rook")points+=5
        else if(board[row][col].substringBefore('_')=="bishop")points+=3
        else if(board[row][col].substringBefore('_')=="queen")points+=9
        else return
        normalizePoints()
    }

    fun updatePointsOpp(row: Int,col: Int){
        if(board[row][col].substringBefore('_')=="pawn")pointsOpp+=1
        else if(board[row][col].substringBefore('_')=="knight")pointsOpp+=3
        else if(board[row][col].substringBefore('_')=="rook")pointsOpp+=5
        else if(board[row][col].substringBefore('_')=="bishop")pointsOpp+=3
        else if(board[row][col].substringBefore('_')=="queen")pointsOpp+=9
        else return
        normalizePoints()
    }

    fun move(positions:ArrayList<Pair<Int,Int>>,fromPos:Pair<Int,Int>,image_view:HashMap<Pair<Int,Int>,ImageView>){
        a1.setOnClickListener {
            if(positions.contains(Pair(7,0))){
                image_view[Pair(7,0)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,7))right_rook_moved=1
                updatePoints(7,0)
                board[7][0]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(7,0))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        a2.setOnClickListener {
            if(positions.contains(Pair(6,0))){
                image_view[Pair(6,0)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,0))left_rook_moved=1
                updatePoints(6,0)
                board[6][0]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(6,0))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        a3.setOnClickListener {
            if(positions.contains(Pair(5,0))){
                image_view[Pair(5,0)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,0))left_rook_moved=1
                updatePoints(5,0)
                board[5][0]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(5,0))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        a4.setOnClickListener {
            if(positions.contains(Pair(4,0))){
                image_view[Pair(4,0)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,0))left_rook_moved=1
                updatePoints(4,0)
                board[4][0]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(4,0))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        a5.setOnClickListener {
            if(positions.contains(Pair(3,0))){
                image_view[Pair(3,0)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,0))left_rook_moved=1
                updatePoints(3,0)
                board[3][0]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(3,0))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        a6.setOnClickListener {
            if(positions.contains(Pair(2,0))){
                image_view[Pair(2,0)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,0))left_rook_moved=1
                updatePoints(2,0)
                board[2][0]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(2,0))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        a7.setOnClickListener {
            if(positions.contains(Pair(1,0))){
                image_view[Pair(1,0)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,0))left_rook_moved=1
                updatePoints(1,0)
                board[1][0]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(1,0))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        a8.setOnClickListener {
            if(positions.contains(Pair(0,0))){
                image_view[Pair(0,0)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,0))left_rook_moved=1
                updatePoints(0,0)
                board[0][0]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                if(board[0][0].substringBefore('_')=="pawn")showPromotionOptions(fromPos,Pair(0,0),image_view)
                else reflectMyMove(fromPos,Pair(0,0))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        b1.setOnClickListener {
            if(positions.contains(Pair(7,1))){
                updatePoints(7,1)
                if(positions.contains(Pair(-2,-2))){
                    king_moved=1
                    image_view[Pair(7,1)]?.setImageResource(R.drawable.king_black)
                    board[7][1]="king_black"
                    image_view[Pair(7,2)]?.setImageResource(R.drawable.rook_black)
                    board[7][2]="rook_black"
                    image_view[Pair(7,0)]?.setImageResource(0)
                    board[7][0]=""
                    image_view[Pair(7,3)]?.setImageResource(0)
                    board[7][3]=""
                    positions.remove(Pair(-2,-2))
                    if(positions.contains(Pair(-1,-2)))positions.remove(Pair(-1,-2))
                    removePositions(positions,image_view)
                    reflectMyMove(fromPos,Pair(7,1),Pair(0,""),Pair(7,0),Pair(7,2))
                }
                else{
                    image_view[Pair(7,1)]?.setImageDrawable(image_view[fromPos]?.drawable)
                    image_view[fromPos]?.setImageResource(0)
                    if(fromPos==Pair(7,7))right_rook_moved=1
                    if(fromPos==Pair(7,0))left_rook_moved=1
                    board[7][1]=board[fromPos.first][fromPos.second]
                    board[fromPos.first][fromPos.second]=""
                    removePositions(positions,image_view)
                    reflectMyMove(fromPos,Pair(7,1))
                }
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        b2.setOnClickListener {
            if(positions.contains(Pair(6,1))){
                image_view[Pair(6,1)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(6,1)
                board[6][1]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(6,1))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        b3.setOnClickListener {
            if(positions.contains(Pair(5,1))){
                image_view[Pair(5,1)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(5,1)
                board[5][1]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(5,1))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        b4.setOnClickListener {
            if(positions.contains(Pair(4,1))){
                image_view[Pair(4,1)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(4,1)
                board[4][1]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(4,1))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        b5.setOnClickListener {
            if(positions.contains(Pair(3,1))){
                image_view[Pair(3,1)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(3,1)
                board[3][1]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(3,1))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        b6.setOnClickListener {
            if(positions.contains(Pair(2,1))){
                image_view[Pair(2,1)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(2,1)
                board[2][1]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(2,1))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        b7.setOnClickListener {
            if(positions.contains(Pair(1,1))){
                image_view[Pair(1,1)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(1,1)
                board[1][1]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(1,1))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        b8.setOnClickListener {
            if(positions.contains(Pair(0,1))){
                image_view[Pair(0,1)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(0,1)
                board[0][1]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                if(board[0][1].substringBefore('_')=="pawn")showPromotionOptions(fromPos,Pair(0,1),image_view)
                else reflectMyMove(fromPos,Pair(0,1))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        c1.setOnClickListener {
            if(positions.contains(Pair(7,2))){
                updatePoints(7,2)
                if(positions.contains(Pair(-2,-1))){
                    king_moved=1
                    image_view[Pair(7,2)]?.setImageResource(R.drawable.king_white)
                    board[7][2]="king_white"
                    image_view[Pair(7,3)]?.setImageResource(R.drawable.rook_white)
                    board[7][3]="rook_white"
                    image_view[Pair(7,0)]?.setImageResource(0)
                    board[7][0]=""
                    image_view[Pair(7,4)]?.setImageResource(0)
                    board[7][4]=""
                    positions.remove(Pair(-2,-1))
                    if(positions.contains(Pair(-1,-1)))positions.remove(Pair(-1,-1))
                    removePositions(positions,image_view)
                    reflectMyMove(fromPos,Pair(7,2),Pair(0,""),Pair(7,0),Pair(7,3))
                }
                else{
                    image_view[Pair(7,2)]?.setImageDrawable(image_view[fromPos]?.drawable)
                    image_view[fromPos]?.setImageResource(0)
                    if(fromPos==Pair(7,7))right_rook_moved=1
                    if(fromPos==Pair(7,0))left_rook_moved=1
                    if(board[fromPos.first][fromPos.second]=="king_black"&&white!=player)king_moved=1
                    board[7][2]=board[fromPos.first][fromPos.second]
                    board[fromPos.first][fromPos.second]=""
                    removePositions(positions,image_view)
                    reflectMyMove(fromPos,Pair(7,2))
                }
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        c2.setOnClickListener {
            if(positions.contains(Pair(6,2))){
                image_view[Pair(6,2)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(6,2)
                if(board[fromPos.first][fromPos.second]=="king_black"&&white!=player)king_moved=1
                board[6][2]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(6,2))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        c3.setOnClickListener {
            if(positions.contains(Pair(5,2))){
                image_view[Pair(5,2)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(5,2)
                board[5][2]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(5,2))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        c4.setOnClickListener {
            if(positions.contains(Pair(4,2))){
                image_view[Pair(4,2)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(4,2)
                board[4][2]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(4,2))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        c5.setOnClickListener {
            if(positions.contains(Pair(3,2))){
                image_view[Pair(3,2)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(3,2)
                board[3][2]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(3,2))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        c6.setOnClickListener {
            if(positions.contains(Pair(2,2))){
                image_view[Pair(2,2)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(2,2)
                board[2][2]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(2,2))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        c7.setOnClickListener {
            if(positions.contains(Pair(1,2))){
                image_view[Pair(1,2)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(1,2)
                board[1][2]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(1,2))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        c8.setOnClickListener {
            if(positions.contains(Pair(0,2))){
                image_view[Pair(0,2)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(0,2)
                board[0][2]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                if(board[0][2].substringBefore('_')=="pawn")showPromotionOptions(fromPos,Pair(0,2),image_view)
                else reflectMyMove(fromPos,Pair(0,2))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        d1.setOnClickListener {
            if(positions.contains(Pair(7,3))){
                image_view[Pair(7,3)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(7,3)
                if(fromPos==Pair(7,7))right_rook_moved=1
                if(fromPos==Pair(7,0))left_rook_moved=1
                if(board[fromPos.first][fromPos.second]=="king_white"&&white==player)king_moved=1
                board[7][3]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(7,3))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        d2.setOnClickListener {
            if(positions.contains(Pair(6,3))){
                image_view[Pair(6,3)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(6,3)
                if(board[fromPos.first][fromPos.second]=="king_black"&&white!=player)king_moved=1
                else if(board[fromPos.first][fromPos.second]=="king_white"&&white==player)king_moved=1
                board[6][3]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(6,3))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        d3.setOnClickListener {
            if(positions.contains(Pair(5,3))){
                image_view[Pair(5,3)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(5,3)
                board[5][3]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(5,3))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        d4.setOnClickListener {
            if(positions.contains(Pair(4,3))){
                image_view[Pair(4,3)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(4,3)
                board[4][3]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(4,3))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        d5.setOnClickListener {
            if(positions.contains(Pair(3,3))){
                image_view[Pair(3,3)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(3,3)
                board[3][3]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(3,3))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        d6.setOnClickListener {
            if(positions.contains(Pair(2,3))){
                image_view[Pair(2,3)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(2,3)
                board[2][3]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(2,3))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        d7.setOnClickListener {
            if(positions.contains(Pair(1,3))){
                image_view[Pair(1,3)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(1,3)
                board[1][3]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(1,3))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        d8.setOnClickListener {
            if(positions.contains(Pair(0,3))){
                image_view[Pair(0,3)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(0,3)
                board[0][3]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                if(board[0][3].substringBefore('_')=="pawn")showPromotionOptions(fromPos,Pair(0,3),image_view)
                else reflectMyMove(fromPos,Pair(0,3))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        e1.setOnClickListener {
            if(positions.contains(Pair(7,4))){
                image_view[Pair(7,4)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(7,4)
                if(fromPos==Pair(7,7))right_rook_moved=1
                if(fromPos==Pair(7,0))left_rook_moved=1
                if(board[fromPos.first][fromPos.second]=="king_black"&&white!=player)king_moved=1
                board[7][4]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(7,4))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        e2.setOnClickListener {
            if(positions.contains(Pair(6,4))){
                image_view[Pair(6,4)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(6,4)
                if(board[fromPos.first][fromPos.second]=="king_black"&&white!=player)king_moved=1
                else if(board[fromPos.first][fromPos.second]=="king_white"&&white==player)king_moved=1
                board[6][4]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(6,4))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        e3.setOnClickListener {
            if(positions.contains(Pair(5,4))){
                image_view[Pair(5,4)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(5,4)
                board[5][4]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(5,4))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        e4.setOnClickListener {
            if(positions.contains(Pair(4,4))){
                image_view[Pair(4,4)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(4,4)
                board[4][4]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(4,4))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        e5.setOnClickListener {
            if(positions.contains(Pair(3,4))){
                image_view[Pair(3,4)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(3,4)
                board[3][4]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(3,4))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        e6.setOnClickListener {
            if(positions.contains(Pair(2,4))){
                image_view[Pair(2,4)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(2,4)
                board[2][4]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(2,4))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        e7.setOnClickListener {
            if(positions.contains(Pair(1,4))){
                image_view[Pair(1,4)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(1,4)
                board[1][4]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(1,4))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        e8.setOnClickListener {
            if(positions.contains(Pair(0,4))){
                image_view[Pair(0,4)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(0,4)
                board[0][4]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                if(board[0][4].substringBefore('_')=="pawn")showPromotionOptions(fromPos,Pair(0,4),image_view)
                else reflectMyMove(fromPos,Pair(0,4))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        f1.setOnClickListener {
            if(positions.contains(Pair(7,5))){
                updatePoints(7,5)
                if(positions.contains(Pair(-1,-2))){
                    king_moved=1
                    image_view[Pair(7,5)]?.setImageResource(R.drawable.king_black)
                    board[7][5]="king_black"
                    image_view[Pair(7,4)]?.setImageResource(R.drawable.rook_black)
                    board[7][4]="rook_black"
                    image_view[Pair(7,7)]?.setImageResource(0)
                    board[7][7]=""
                    image_view[Pair(7,3)]?.setImageResource(0)
                    board[7][3]=""
                    positions.remove(Pair(-1,-2))
                    if(positions.contains(Pair(-2,-2)))positions.remove(Pair(-2,-2))
                    removePositions(positions,image_view)
                    reflectMyMove(fromPos,Pair(7,5),Pair(0,""),Pair(7,7),Pair(7,4))
                }
                else{
                    image_view[Pair(7,5)]?.setImageDrawable(image_view[fromPos]?.drawable)
                    image_view[fromPos]?.setImageResource(0)
                    if(fromPos==Pair(7,0))left_rook_moved=1
                    if(fromPos==Pair(7,7))right_rook_moved=1
                    if(board[fromPos.first][fromPos.second]=="king_white"&&white==player)king_moved=1
                    board[7][5]=board[fromPos.first][fromPos.second]
                    board[fromPos.first][fromPos.second]=""
                    removePositions(positions,image_view)
                    reflectMyMove(fromPos,Pair(7,5))

                }
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        f2.setOnClickListener {
            if(positions.contains(Pair(6,5))){
                image_view[Pair(6,5)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(6,5)
                if(board[fromPos.first][fromPos.second]=="king_white"&&white==player)king_moved=1
                board[6][5]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(6,5))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        f3.setOnClickListener {
            if(positions.contains(Pair(5,5))){
                image_view[Pair(5,5)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(5,5)
                board[5][5]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(5,5))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        f4.setOnClickListener {
            if(positions.contains(Pair(4,5))){
                image_view[Pair(4,5)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(4,5)
                board[4][5]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(4,5))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        f5.setOnClickListener {
            if(positions.contains(Pair(3,5))){
                image_view[Pair(3,5)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(3,5)
                board[3][5]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(3,5))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        f6.setOnClickListener {
            if(positions.contains(Pair(2,5))){
                image_view[Pair(2,5)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(2,5)
                board[2][5]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(2,5))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        f7.setOnClickListener {
            if(positions.contains(Pair(1,5))){
                image_view[Pair(1,5)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(1,5)
                board[1][5]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(1,5))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        f8.setOnClickListener {
            if(positions.contains(Pair(0,5))){
                image_view[Pair(0,5)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(0,5)
                board[0][5]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                if(board[0][5].substringBefore('_')=="pawn")showPromotionOptions(fromPos,Pair(0,5),image_view)
                else reflectMyMove(fromPos,Pair(0,5))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        g1.setOnClickListener {
            if(positions.contains(Pair(7,6))){
                updatePoints(7,6)
                if(positions.contains(Pair(-1,-1))){
                    king_moved=1
                    image_view[Pair(7,6)]?.setImageResource(R.drawable.king_white)
                    board[7][6]="king_white"
                    image_view[Pair(7,5)]?.setImageResource(R.drawable.rook_white)
                    board[7][5]="rook_white"
                    image_view[Pair(7,7)]?.setImageResource(0)
                    board[7][7]=""
                    image_view[Pair(7,4)]?.setImageResource(0)
                    board[7][4]=""
                    positions.remove(Pair(-1,-1))
                    if(positions.contains(Pair(-2,-1)))positions.remove(Pair(-2,-1))
                    removePositions(positions,image_view)
                    reflectMyMove(fromPos,Pair(7,6),Pair(0,""),Pair(7,7),Pair(7,5))
                }
                else{
                    image_view[Pair(7,6)]?.setImageDrawable(image_view[fromPos]?.drawable)
                    image_view[fromPos]?.setImageResource(0)
                    if(fromPos==Pair(7,7))right_rook_moved=1
                    if(fromPos==Pair(7,0))left_rook_moved=1
                    board[7][6]=board[fromPos.first][fromPos.second]
                    board[fromPos.first][fromPos.second]=""
                    removePositions(positions,image_view)
                    reflectMyMove(fromPos,Pair(7,6))

                }
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        g2.setOnClickListener {
            if(positions.contains(Pair(6,6))){
                image_view[Pair(6,6)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(6,6)
                board[6][6]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(6,6))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        g3.setOnClickListener {
            if(positions.contains(Pair(5,6))){
                image_view[Pair(5,6)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(6,6)
                board[5][6]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(5,6))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        g4.setOnClickListener {
            if(positions.contains(Pair(4,6))){
                image_view[Pair(4,6)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(4,6)
                board[4][6]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(4,6))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        g5.setOnClickListener {
            if(positions.contains(Pair(3,6))){
                image_view[Pair(3,6)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(3,6)
                board[3][6]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(3,6))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        g6.setOnClickListener {
            if(positions.contains(Pair(2,6))){
                image_view[Pair(2,6)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(2,6)
                board[2][6]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(2,6))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        g7.setOnClickListener {
            if(positions.contains(Pair(1,6))){
                image_view[Pair(1,6)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(1,6)
                board[1][6]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(1,6))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        g8.setOnClickListener {
            if(positions.contains(Pair(0,6))){
                image_view[Pair(0,6)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                updatePoints(0,6)
                board[0][6]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                if(board[0][6].substringBefore('_')=="pawn")showPromotionOptions(fromPos,Pair(0,6),image_view)
                else reflectMyMove(fromPos,Pair(0,6))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        h1.setOnClickListener {
            if(positions.contains(Pair(7,7))){
                image_view[Pair(7,7)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,0))left_rook_moved=1
                updatePoints(7,7)
                board[7][7]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(7,7))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        h2.setOnClickListener {
            if(positions.contains(Pair(6,7))){
                image_view[Pair(6,7)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,7))right_rook_moved=1
                updatePoints(6,7)
                board[6][7]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(6,7))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        h3.setOnClickListener {
            if(positions.contains(Pair(5,7))){
                image_view[Pair(5,7)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,7))right_rook_moved=1
                updatePoints(5,7)
                board[5][7]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(5,7))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        h4.setOnClickListener {
            if(positions.contains(Pair(4,7))){
                image_view[Pair(4,7)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,7))right_rook_moved=1
                updatePoints(4,7)
                board[4][7]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(4,7))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        h5.setOnClickListener {
            if(positions.contains(Pair(3,7))){
                image_view[Pair(3,7)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,7))right_rook_moved=1
                updatePoints(3,7)
                board[3][7]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(3,7))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        h6.setOnClickListener {
            if(positions.contains(Pair(2,7))){
                image_view[Pair(2,7)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,7))right_rook_moved=1
                updatePoints(2,7)
                board[2][7]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(2,7))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        h7.setOnClickListener {
            if(positions.contains(Pair(1,7))){
                image_view[Pair(1,7)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,7))right_rook_moved=1
                updatePoints(1,7)
                board[1][7]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                reflectMyMove(fromPos,Pair(1,7))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
        h8.setOnClickListener {
            if(positions.contains(Pair(0,7))){
                image_view[Pair(0,7)]?.setImageDrawable(image_view[fromPos]?.drawable)
                image_view[fromPos]?.setImageResource(0)
                if(fromPos==Pair(7,7))right_rook_moved=1
                updatePoints(0,7)
                board[0][7]=board[fromPos.first][fromPos.second]
                board[fromPos.first][fromPos.second]=""
                removePositions(positions,image_view)
                if(board[0][7].substringBefore('_')=="pawn")showPromotionOptions(fromPos,Pair(0,7),image_view)
                else reflectMyMove(fromPos,Pair(0,7))
                removeOnClickListeners(image_view)
                makeMove(image_view,1)
            }
            else{
                removePositions(positions,image_view)
                removeOnClickListeners(image_view)
                makeMove(image_view,0)
            }
        }
    }

    fun makeMove(image_view:HashMap<Pair<Int,Int>,ImageView>,flag:Int){
        if(flag==1)return
        var positions:ArrayList<Pair<Int,Int>> = arrayListOf()
        a1.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(7,0)){
                positions.addAll(validPositions(7,0,board[7][0].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(7, 0), image_view)
                }
            }
        }
        a2.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(6,0)){
                positions.addAll(validPositions(6, 0, board[6][0].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions,Pair(6,0),image_view)
                }
            }
        }
        a3.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(5,0)){
                positions.addAll(validPositions(5, 0, board[5][0].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(5, 0), image_view)
                }
            }
        }
        a4.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(4,0)){
                positions.addAll(validPositions(4, 0, board[4][0].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(4, 0), image_view)
                }
            }
        }
        a5.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(3,0)){
                positions.addAll(validPositions(3, 0, board[3][0].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(3, 0), image_view)
                }
            }
        }
        a6.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(2,0)){
                positions.addAll(validPositions(2, 0, board[2][0].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(2, 0), image_view)
                }
            }
        }
        a7.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(1,0)){
                positions.addAll(validPositions(1, 0, board[1][0].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(1, 0), image_view)
                }
            }
        }
        a8.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(0,0)){
                positions.addAll(validPositions(0, 0, board[0][0].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(0, 0), image_view)
                }
            }
        }
        b1.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(7,1)){
                positions.addAll(validPositions(7, 1, board[7][1].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(7, 1), image_view)
                }
            }
        }
        b2.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(6,1)){
                positions.addAll(validPositions(6, 1, board[6][1].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(6,1), image_view)
                }
            }
        }
        b3.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(5,1)){
                positions.addAll(validPositions(5, 1, board[5][1].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(5,1), image_view)
                }
            }
        }
        b4.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(4,1)){
                positions.addAll(validPositions(4, 1, board[4][1].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(4,1), image_view)
                }
            }
        }
        b5.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(3,1)){
                positions.addAll(validPositions(3, 1, board[3][1].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(3,1), image_view)
                }
            }
        }
        b6.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(2,1)){
                positions.addAll(validPositions(2, 1, board[2][1].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(2,1), image_view)

                }
            }
        }
        b7.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(1,1)){
                positions.addAll(validPositions(1, 1, board[1][1].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(1,1), image_view)
                }
            }
        }
        b8.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(0,1)){
                positions.addAll(validPositions(0, 1, board[0][1].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(0,1), image_view)
                }
            }
        }
        c1.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(7,2)){
                positions.addAll(validPositions(7, 2, board[7][2].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(7, 2), image_view)
                }
            }
        }
        c2.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(6,2)){
                positions.addAll(validPositions(6, 2, board[6][2].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(6,2), image_view)
                }
            }
        }
        c3.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(5,2)){
                positions.addAll(validPositions(5, 2, board[5][2].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(5,2), image_view)
                }
            }
        }
        c4.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(4,2)){
                positions.addAll(validPositions(4, 2, board[4][2].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(4,2), image_view)
                }
            }
        }
        c5.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(3,2)){
                positions.addAll(validPositions(3, 2, board[3][2].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(3,2), image_view)
                }
            }
        }
        c6.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(2,2)){
                positions.addAll(validPositions(2, 2, board[2][2].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(2,2), image_view)
                }
            }
        }
        c7.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(1,2)){
                positions.addAll(validPositions(1, 2, board[1][2].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(1,2), image_view)
                }
            }
        }
        c8.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(0,2)){
                positions.addAll(validPositions(0, 2, board[0][2].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(0,2), image_view)
                }
            }
        }
        d1.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(7,3)){
                positions.addAll(validPositions(7, 3, board[7][3].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(7, 3), image_view)
                }
            }
        }
        d2.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(6,3)){
                positions.addAll(validPositions(6, 3, board[6][3].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(6,3), image_view)
                }
            }
        }
        d3.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(5,3)){
                positions.addAll(validPositions(5, 3, board[5][3].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(5,3), image_view)
                }
            }
        }
        d4.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(4,3)){
                positions.addAll(validPositions(4, 3, board[4][3].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(4,3), image_view)
                }
            }
        }
        d5.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(3,3)){
                positions.addAll(validPositions(3, 3, board[3][3].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(3,3), image_view)
                }
            }
        }
        d6.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(2,3)){
                positions.addAll(validPositions(2, 3, board[2][3].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(2,3), image_view)
                }
            }
        }
        d7.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(1,3)){
                positions.addAll(validPositions(1, 3, board[1][3].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(1,3), image_view)
                }
            }
        }
        d8.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(0,3)){
                positions.addAll(validPositions(0, 3, board[0][3].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(0,3), image_view)
                }
            }
        }
        e1.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(7,4)){
                positions.addAll(validPositions(7, 4, board[7][4].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(7, 4), image_view)
                }
            }
        }
        e2.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(6,4)){
                positions.addAll(validPositions(6, 4, board[6][4].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(6,4), image_view)
                }
            }
        }
        e3.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(5,4)){
                positions.addAll(validPositions(5,4,board[5][4].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions,image_view)
                    move(positions, Pair(5,4), image_view)
                }
            }
        }
        e4.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(4,4)){
                positions.addAll(validPositions(4, 4, board[4][4].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(4,4), image_view)
                }
            }
        }
        e5.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(3,4)){
                positions.addAll(validPositions(3, 4, board[3][4].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(3,4), image_view)
                }
            }
        }
        e6.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(2,4)){
                positions.addAll(validPositions(2, 4, board[2][4].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(2,4), image_view)
                }
            }
        }
        e7.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(1,4)){
                positions.addAll(validPositions(1, 4, board[1][4].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(1,4), image_view)
                }
            }
        }
        e8.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(0,4)){
                positions.addAll(validPositions(0, 4, board[0][4].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(0,4), image_view)
                }
            }
        }
        f1.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(7,5)){
                positions.addAll(validPositions(7, 5, board[7][5].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(7, 5), image_view)
                }
            }
        }
        f2.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(6,5)){
                positions.addAll(validPositions(6, 5, board[6][5].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(6,5), image_view)
                }
            }
        }
        f3.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(5,5)){
                positions.addAll(validPositions(5, 5, board[5][5].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(5,5), image_view)
                }
            }
        }
        f4.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(4,5)){
                positions.addAll(validPositions(4, 5, board[4][5].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(4,5), image_view)
                }
            }
        }
        f5.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(3,5)){
                positions.addAll(validPositions(3, 5, board[3][5].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(3,5), image_view)
                }
            }
        }
        f6.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(2,5)){
                positions.addAll(validPositions(2, 5, board[2][5].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(2,5), image_view)
                }
            }
        }
        f7.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(1,5)){
                positions.addAll(validPositions(1, 5, board[1][5].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(1,5), image_view)
                }
            }
        }
        f8.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(0,5)){
                positions.addAll(validPositions(0, 5, board[0][5].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(0,5), image_view)
                }
            }
        }
        g1.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(7,6)){
                positions.addAll(validPositions(7, 6, board[7][6].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(7, 6), image_view)
                }
            }
        }
        g2.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(6,6)){
                positions.addAll(validPositions(6, 6, board[6][6].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(6,6), image_view)
                }
            }
        }
        g3.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(5,6)){
                positions.addAll(validPositions(5, 6, board[5][6].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(5,6), image_view)
                }
            }
        }
        g4.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(4,6)){
                positions.addAll(validPositions(4, 6, board[4][6].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(4,6), image_view)
                }
            }
        }
        g5.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(3,6)){
                positions.addAll(validPositions(3, 6, board[3][6].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(3,6), image_view)
                }
            }
        }
        g6.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(2,6)){
                positions.addAll(validPositions(2, 6, board[2][6].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(2,6), image_view)
                }
            }
        }
        g7.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(1,6)){
                positions.addAll(validPositions(1, 6, board[1][6].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(1,6), image_view)
                }
            }
        }
        g8.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(0,6)){
                positions.addAll(validPositions(0, 6, board[0][6].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(0,6), image_view)
                }
            }
        }
        h1.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(7,7)){
                positions.addAll(validPositions(7, 7, board[7][7].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(7, 7), image_view)
                }
            }
        }
        h2.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(6,7)){
                positions.addAll(validPositions(6, 7, board[6][7].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(6,7), image_view)
                }
            }
        }
        h3.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(5,7)){
                positions.addAll(validPositions(5, 7, board[5][7].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(5,7), image_view)
                }
            }
        }
        h4.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(4,7)){
                positions.addAll(validPositions(4, 7, board[4][7].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(4,7), image_view)
                }
            }
        }
        h5.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(3,7)){
                positions.addAll(validPositions(3, 7, board[3][7].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(3,7), image_view)
                }
            }
        }
        h6.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(2,7)){
                positions.addAll(validPositions(2, 7, board[2][7].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(2,7), image_view)
                }
            }
        }
        h7.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(1,7)){
                positions.addAll(validPositions(1, 7, board[1][7].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions, image_view)
                    move(positions, Pair(1,7), image_view)
                }
            }
        }
        h8.setOnClickListener {
            removePositions(positions,image_view)
            positions.clear()
            if(validClick(0,7)){
                positions.addAll(validPositions(0,7,board[0][7].substringBefore('_')))
                if(positions.isNotEmpty()) {
                    displayPositions(positions,image_view)
                    move(positions, Pair(0,7), image_view)
                }
            }
        }
    }

    private lateinit var countDownTimer: CountDownTimer
    var timeLeftInMillis: Long = 0
    var increment: Long = 0
    private var isTimerRunning: Boolean = false

    fun setTimer(time:Long,inc:Long){
        timeLeftInMillis=time
        increment=inc
    }

    private fun updateTimer() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60

        val timeFormatted = String.format("%02d:%02d", minutes, seconds)
        player1Timer.text = timeFormatted
    }

    fun startTimer(){
        countDownTimer=object:CountDownTimer(timeLeftInMillis,1000){
            override fun onTick(millisUntilFinished:Long) {
                timeLeftInMillis=millisUntilFinished
                updateTimer()
            }
            override fun onFinish() {
                endGame("timeout","loss")
            }
        }.start()
        isTimerRunning=true
    }

    private fun pauseTimer() {
        if (isTimerRunning) {
            timeLeftInMillis+=increment
            updateTimer()
            countDownTimer.cancel()
            isTimerRunning = false
        }
    }

    private lateinit var countDownTimerOpp: CountDownTimer
    var timeLeftInMillisOpp: Long = 0
    private var isTimerRunningOpp: Boolean = false

    fun setTimerOpp(time:Long){
        timeLeftInMillisOpp=time
    }

    private fun updateTimerOpp() {
        val minutes = (timeLeftInMillisOpp / 1000) / 60
        val seconds = (timeLeftInMillisOpp / 1000) % 60

        val timeFormatted = String.format("%02d:%02d", minutes, seconds)
        player2Timer.text = timeFormatted
    }

    fun startTimerOpp(){
        countDownTimerOpp=object:CountDownTimer(timeLeftInMillisOpp,1000){
            override fun onTick(millisUntilFinished:Long) {
                timeLeftInMillisOpp=millisUntilFinished
                updateTimerOpp()
            }
            override fun onFinish() {
                endGame("timeout","win")
            }
        }.start()
        isTimerRunningOpp=true
    }

    private fun pauseTimerOpp() {
        if (isTimerRunningOpp) {
            timeLeftInMillisOpp+=increment
            updateTimerOpp()
            countDownTimerOpp.cancel()
            isTimerRunningOpp = false
        }
    }

    fun endGame(by:String,status:String){
        pauseTimerOpp()
        pauseTimer()
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@Game)
        if(status=="win"){
            if(by=="checkmate")alertDialog.setMessage("You Win! By Checkmate")
            else if(by=="resignation")alertDialog.setMessage("You Win! By Resignation")
            else if(by=="timeout")alertDialog.setMessage("You Win! By Timeout")
        }
        else if(status=="loss"){
            if(by=="checkmate")alertDialog.setMessage("You Lost. By Checkmate")
            else if(by=="resignation")alertDialog.setMessage("You Lost. By Resignation")
            else if(by=="timeout")alertDialog.setMessage("You Lost. By Timeout")
        }
        else{
            if(by=="stalemate")alertDialog.setMessage("Draw. By Stalemate")
            else alertDialog.setMessage("Draw. By Agreement")
        }
        alertDialog.setPositiveButton(
            "Return To Home"
        ) { _, _ ->
            val intent= Intent(this,HomeScreen::class.java)
            intent.putExtra("email",email)
            intent.putExtra("id",userId)
            startActivity(intent)
        }
//        alertDialog.setNegativeButton(
//            "Rematch"
//        ) { _, _ ->
//
//        }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }

    fun checkTurnRealTime(image_view:HashMap<Pair<Int,Int>,ImageView>){
        val docRef = firestore.collection("games").document(game_id)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val doc= snapshot.data as HashMap<*, *>
                turn= doc["turn"].toString()
                if(turn==player&&doc["player1"]=="online"&&doc["player2"]=="online"){
                    player1Timer.setBackgroundColor(ContextCompat.getColor(this, R.color.hint_color))
                    player2Timer.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
                    reflectOppMove(image_view,doc)
                    if(player==white){
                        pauseTimerOpp()
                        if(doc["whiteCheckmate"]=="yes"){
                            endGame("checkmate","loss")
                        }
                        else if(isCheckmate()){
                            if(isCheck().first==0){
                                val drawResignDocumentRef = drawResignDocumentId.let { it1 -> firestore.collection("draw_resign").document(it1) }
                                drawResignDocumentRef.update("stalemate", true)
                            }
                            else{
                                var gameDocumentRef = game_id.let { it1 -> firestore.collection("games").document(it1) }
                                var updates = hashMapOf(
                                    "whiteCheckmate" to "yes"
                                )
                                gameDocumentRef.update(updates as Map<String, Any>)
                            }
                        }
                        else{
                            timeLeftInMillis=doc["timer1"] as Long
                            startTimer()
                            makeMove(image_view,0)
                        }
                    }
                    else{
                        pauseTimerOpp()
                        if(doc["blackCheckmate"]=="yes"){
                            endGame("checkmate","loss")
                        }
                        else if(isCheckmate()){
                            if(isCheck().first==0){
                                val drawResignDocumentRef = drawResignDocumentId.let { it1 -> firestore.collection("draw_resign").document(it1) }
                                drawResignDocumentRef.update("stalemate", true)
                            }
                            else{
                                var gameDocumentRef = game_id.let { it1 -> firestore.collection("games").document(it1) }
                                var updates = hashMapOf(
                                    "blackCheckmate" to "yes"
                                )
                                gameDocumentRef.update(updates as Map<String, Any>)
                            }
                        }
                        else{
                            timeLeftInMillis=doc["timer2"] as Long
                            startTimer()
                            makeMove(image_view,0)
                        }
                    }
                }
                else if(turn!=player&&doc["player1"]=="online"&&doc["player2"]=="online"){
                    player1Timer.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
                    player2Timer.setBackgroundColor(ContextCompat.getColor(this, R.color.hint_color))
                    if(player==white){
                        timeLeftInMillisOpp=doc["timer2"] as Long
                        startTimerOpp()
                        if(doc["blackCheckmate"]=="yes")endGame("checkmate","win")
                    }
                    else{
                        timeLeftInMillisOpp=doc["timer1"] as Long
                        startTimerOpp()
                        if(doc["whiteCheckmate"]=="yes")endGame("checkmate","win")
                    }
                }
            }
        }
    }

    fun checkDrawRealTime() {
        val docRef = firestore.collection("draw_resign").document(drawResignDocumentId)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val doc = snapshot.data
                val draw = doc?.get("draw").toString()
                val Resign = doc?.get("resign") as? Boolean
                val stalemate = doc?.get("stalemate") as? Boolean

                if(Resign == true &&!resign){
                    endGame("resignation","win")
                }
                else if(Resign == true &&resign) {
                    endGame("resignation", "loss")
                }
                if (draw=="1" && !drawOffered) {
                    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@Game)
                    alertDialog.setMessage("$usernameplayer2 has offered a draw.")
                    alertDialog.setPositiveButton(
                        "Accept"
                    ) { _, _ ->
                        val drawResignDocumentRef = drawResignDocumentId.let { it1 -> firestore.collection("draw_resign").document(it1) }
                        drawResignDocumentRef.update("draw", -1)
                        endGame("agreement", "draw")
                    }
                    alertDialog.setNegativeButton(
                        "Reject"
                    ) { _, _ ->
                        val drawResignDocumentRef = drawResignDocumentId.let { it1 -> firestore.collection("draw_resign").document(it1) }
                        drawResignDocumentRef.update("draw", 0)
                    }
                    val alert: AlertDialog = alertDialog.create()
                    alert.setCanceledOnTouchOutside(true)
                    alert.show()
                } else if (draw=="-1" && drawOffered) {
                    endGame("agreement", "draw")
                }
                else if(draw=="0" && drawOffered){
                    drawOffered=false
                }
                else if(stalemate==true){
                    endGame("stalemate","draw")
                }
            } else {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)

        val drawBtn=findViewById<Button>(R.id.drawBtn)
        val resignBtn=findViewById<Button>(R.id.resignBtn)

        grid=findViewById(R.id.gridLayout)

        a8 =findViewById(R.id.imageView1)
        a7 =findViewById(R.id.imageView9)
        a6 =findViewById(R.id.imageView17)
        a5 =findViewById(R.id.imageView25)
        a4 =findViewById(R.id.imageView33)
        a3 =findViewById(R.id.imageView41)
        a2 =findViewById(R.id.imageView49)
        a1 =findViewById(R.id.imageView57)
        b8 =findViewById(R.id.imageView2)
        b7 =findViewById(R.id.imageView10)
        b6 =findViewById(R.id.imageView18)
        b5 =findViewById(R.id.imageView26)
        b4 =findViewById(R.id.imageView34)
        b3 =findViewById(R.id.imageView42)
        b2 =findViewById(R.id.imageView50)
        b1 =findViewById(R.id.imageView58)
        c8 =findViewById(R.id.imageView3)
        c7 =findViewById(R.id.imageView11)
        c6 =findViewById(R.id.imageView19)
        c5 =findViewById(R.id.imageView27)
        c4 =findViewById(R.id.imageView35)
        c3 =findViewById(R.id.imageView43)
        c2 =findViewById(R.id.imageView51)
        c1 =findViewById(R.id.imageView59)
        d8 =findViewById(R.id.imageView4)
        d7 =findViewById(R.id.imageView12)
        d6 =findViewById(R.id.imageView20)
        d5 =findViewById(R.id.imageView28)
        d4 =findViewById(R.id.imageView36)
        d3 =findViewById(R.id.imageView44)
        d2 =findViewById(R.id.imageView52)
        d1 =findViewById(R.id.imageView60)
        e8 =findViewById(R.id.imageView5)
        e7 =findViewById(R.id.imageView13)
        e6 =findViewById(R.id.imageView21)
        e5 =findViewById(R.id.imageView29)
        e4 =findViewById(R.id.imageView37)
        e3 =findViewById(R.id.imageView45)
        e2 =findViewById(R.id.imageView53)
        e1 =findViewById(R.id.imageView61)
        f8 =findViewById(R.id.imageView6)
        f7 =findViewById(R.id.imageView14)
        f6 =findViewById(R.id.imageView22)
        f5 =findViewById(R.id.imageView30)
        f4 =findViewById(R.id.imageView38)
        f3 =findViewById(R.id.imageView46)
        f2 =findViewById(R.id.imageView54)
        f1 =findViewById(R.id.imageView62)
        g8 =findViewById(R.id.imageView7)
        g7 =findViewById(R.id.imageView15)
        g6 =findViewById(R.id.imageView23)
        g5 =findViewById(R.id.imageView31)
        g4 =findViewById(R.id.imageView39)
        g3 =findViewById(R.id.imageView47)
        g2 =findViewById(R.id.imageView55)
        g1 =findViewById(R.id.imageView63)
        h8 =findViewById(R.id.imageView8)
        h7 =findViewById(R.id.imageView16)
        h6 =findViewById(R.id.imageView24)
        h5 =findViewById(R.id.imageView32)
        h4 =findViewById(R.id.imageView40)
        h3 =findViewById(R.id.imageView48)
        h2 =findViewById(R.id.imageView56)
        h1 =findViewById(R.id.imageView64)

        player1Image=findViewById<ImageView>(R.id.player1Image)
        player1Username=findViewById<TextView>(R.id.player1Username)
        player1Timer=findViewById<TextView>(R.id.player1Timer)

        player2Image=findViewById<ImageView>(R.id.player2Image)
        player2Username=findViewById<TextView>(R.id.player2Username)
        player2Timer=findViewById<TextView>(R.id.player2Timer)

        val image_view = hashMapOf(
            Pair(0, 0) to a8,
            Pair(4, 4) to e4,
            Pair(0, 1) to b8,
            Pair(4, 5) to f4,
            Pair(0, 2) to c8,
            Pair(4, 6) to g4,
            Pair(0, 3) to d8,
            Pair(4, 7) to h4,
            Pair(0, 4) to e8,
            Pair(0, 5) to f8,
            Pair(0, 6) to g8,
            Pair(0, 7) to h8,
            Pair(5, 0) to a3,
            Pair(5, 1) to b3,
            Pair(5, 2) to c3,
            Pair(5, 3) to d3,
            Pair(1, 0) to a7,
            Pair(5, 4) to e3,
            Pair(1, 1) to b7,
            Pair(5, 5) to f3,
            Pair(1, 2) to c7,
            Pair(5, 6) to g3,
            Pair(1, 3) to d7,
            Pair(5, 7) to h3,
            Pair(1, 4) to e7,
            Pair(1, 5) to f7,
            Pair(1, 6) to g7,
            Pair(1, 7) to h7,
            Pair(6, 0) to a2,
            Pair(6, 1) to b2,
            Pair(6, 2) to c2,
            Pair(6, 3) to d2,
            Pair(2, 0) to a6,
            Pair(6, 4) to e2,
            Pair(2, 1) to b6,
            Pair(6, 5) to f2,
            Pair(2, 2) to c6,
            Pair(6, 6) to g2,
            Pair(2, 3) to d6,
            Pair(6, 7) to h2,
            Pair(2, 4) to e6,
            Pair(2, 5) to f6,
            Pair(2, 6) to g6,
            Pair(2, 7) to h6,
            Pair(7, 0) to a1,
            Pair(7, 1) to b1,
            Pair(7, 2) to c1,
            Pair(7, 3) to d1,
            Pair(3, 0) to a5,
            Pair(7, 4) to e1,
            Pair(3, 1) to b5,
            Pair(7, 5) to f1,
            Pair(3, 2) to c5,
            Pair(7, 6) to g1,
            Pair(3, 3) to d5,
            Pair(7, 7) to h1,
            Pair(3, 4) to e5,
            Pair(3, 5) to f5,
            Pair(3, 6) to g5,
            Pair(3, 7) to h5,
            Pair(4, 0) to a4,
            Pair(4, 1) to b4,
            Pair(4, 2) to c4,
            Pair(4, 3) to d4
        )

        email = intent.extras!!.getString("email").toString()
        val oppEmail = intent.extras!!.getString("oppEmail").toString()

        firestore.collection("users")
            .whereEqualTo("email", oppEmail)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    usernameplayer2 = document.getString("username").toString()
                }
            }
            .addOnFailureListener {
            }

        firestore.collection("users")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    gameId = document.getString("gameId").toString()
                    player = document.getString("player").toString()
                    userId = document.id
                    usernameplayer1= document.getString("username").toString()
                    firestore.collection("games")
                        .whereEqualTo("gameId", gameId)
                        .get()
                        .addOnSuccessListener { Documents ->
                            for (Document in Documents) {
                                white = Document.getString("white").toString()
                                game_id=Document.id
                                val time=Document.getLong("timer1")
                                val inc=Document.getLong("increment")
                                if(time!=null&&inc!=null){
                                    setTimer(time,inc)
                                    setTimerOpp(time)
                                }
                                drawResignDocumentId=Document.getString("drawResignDocumentId").toString()

                                resignBtn.setOnClickListener {
                                    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@Game)
                                    alertDialog.setMessage("Do you want to Resign?")
                                    alertDialog.setPositiveButton(
                                        "Yes"
                                    ) { _, _ ->
                                        resign=true
                                        val drawResignDocumentRef = drawResignDocumentId.let { it1 -> firestore.collection("draw_resign").document(it1) }
                                        drawResignDocumentRef.update("resign", true)
                                    }
                                    alertDialog.setNegativeButton(
                                        "No"
                                    ) { _, _ ->

                                    }
                                    val alert: AlertDialog = alertDialog.create()
                                    alert.setCanceledOnTouchOutside(false)
                                    alert.show()
                                }

                                checkDrawRealTime()

                                drawBtn.setOnClickListener {
                                    if(!drawOffered){
                                        Toast.makeText(this,"Draw Offered.",Toast.LENGTH_SHORT).show()
                                        drawOffered=true
                                        val drawResignDocumentRef = drawResignDocumentId.let { it1 -> firestore.collection("draw_resign").document(it1) }
                                        drawResignDocumentRef.update("draw", 1)
                                    }
                                }
                                if(time!=null)setBoard(time.toInt())
                                checkTurnRealTime(image_view)
                            }
                        }
                        .addOnFailureListener {
                        }
                }
            }
            .addOnFailureListener {
            }
    }
}