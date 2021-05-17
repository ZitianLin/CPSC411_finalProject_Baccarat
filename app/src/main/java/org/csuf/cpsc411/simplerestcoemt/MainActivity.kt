package org.csuf.cpsc411.simplerestcoemt

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import kotlinx.android.synthetic.main.user_interface.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.anko.*
import org.jetbrains.anko.db.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(){

    // function to draw and create the third card of banker
    // update all the information
    fun banker_draw_fun(banker_score: Int, index: Int, numbers : Array<Card>): Int{
        val banker_image3 : ImageView = findViewById(R.id.banker_image_3)
        banker_image3.visibility = View.VISIBLE
        banker_image3.setImageResource(numbers[index].image)
        var new_banker_score = banker_score + numbers[index].value
        new_banker_score = new_banker_score % 10
        val banker_text: TextView = findViewById(R.id.banker_score)
        banker_text.setText(new_banker_score.toString())
        return numbers[index].value
    }

    // function to draw and create the third card of banker
    // update all the information
    fun player_draw_fun(player_score: Int, index: Int, numbers : Array<Card>) : Int{
        val player_image3 : ImageView = findViewById(R.id.player_image_3)
        player_image3.visibility = View.VISIBLE
        player_image3.setImageResource(numbers[index].image)
        var new_player_score = player_score + numbers[index].value
        new_player_score = new_player_score % 10
        val player_text: TextView = findViewById(R.id.player_score)
        player_text.setText(new_player_score.toString())
        return numbers[index].value
    }

    // function to follow the rules and deside which group is winning
    fun calculationhelper(b_score:Int, p_score:Int, index: Int, numbers : Array<Card>) : Int{
        var banker_score = b_score
        var player_score = p_score
        var newIndex : Int = index

        // Case: Player gets natural
        if(player_score > 7){
            // player wins
            if(player_score > banker_score) {
                var total_chips : TextView = findViewById(R.id.chips_number)
                var bet : TextView = findViewById(R.id.bet_number)
                var banker_bet : TextView = findViewById(R.id.banker_number)
                var player_bet : TextView = findViewById(R.id.player_number)
                var dragon_bet : TextView = findViewById(R.id.dragon_number)
                var tie_bet : TextView = findViewById(R.id.tie_number)
                var panda_bet : TextView = findViewById(R.id.panda_number)

                var player_bet_int : Int = player_bet.text.toString().toInt()
                var total_chips_int :Int = total_chips.text.toString().toInt()
                total_chips_int = total_chips_int + player_bet_int * 2
                total_chips.setText(total_chips_int.toString())

                bet.setText((0).toString())
                banker_bet.setText((0).toString())
                player_bet.setText((0).toString())
                dragon_bet.setText((0).toString())
                tie_bet.setText((0).toString())
                panda_bet.setText((0).toString())

                var define : TextView = findViewById(R.id.define_text)
                define.setText("Player wins")
                return newIndex
            }
            // banker wins
            else if (player_score < banker_score){
                var total_chips : TextView = findViewById(R.id.chips_number)
                var bet : TextView = findViewById(R.id.bet_number)
                var banker_bet : TextView = findViewById(R.id.banker_number)
                var player_bet : TextView = findViewById(R.id.player_number)
                var dragon_bet : TextView = findViewById(R.id.dragon_number)
                var tie_bet : TextView = findViewById(R.id.tie_number)
                var panda_bet : TextView = findViewById(R.id.panda_number)

                var banker_bet_int : Int = banker_bet.text.toString().toInt()
                var total_chips_int :Int = total_chips.text.toString().toInt()
                total_chips_int = total_chips_int + banker_bet_int * 2
                total_chips.setText(total_chips_int.toString())

                bet.setText((0).toString())
                banker_bet.setText((0).toString())
                player_bet.setText((0).toString())
                dragon_bet.setText((0).toString())
                tie_bet.setText((0).toString())
                panda_bet.setText((0).toString())

                var define : TextView = findViewById(R.id.define_text)
                define.setText("Banker wins")
                return newIndex
            }
            // Tie
            else{
                var total_chips : TextView = findViewById(R.id.chips_number)
                var bet : TextView = findViewById(R.id.bet_number)
                var banker_bet : TextView = findViewById(R.id.banker_number)
                var player_bet : TextView = findViewById(R.id.player_number)
                var dragon_bet : TextView = findViewById(R.id.dragon_number)
                var tie_bet : TextView = findViewById(R.id.tie_number)
                var panda_bet : TextView = findViewById(R.id.panda_number)

                var tie_bet_int : Int = tie_bet.text.toString().toInt()
                var total_chips_int :Int = total_chips.text.toString().toInt()
                var banker_bet_int : Int = banker_bet.text.toString().toInt()
                var player_bet_int : Int = player_bet.text.toString().toInt()

                total_chips_int = total_chips_int + tie_bet_int * 8 + banker_bet_int + player_bet_int
                total_chips.setText(total_chips_int.toString())

                bet.setText((0).toString())
                banker_bet.setText((0).toString())
                player_bet.setText((0).toString())
                dragon_bet.setText((0).toString())
                tie_bet.setText((0).toString())
                panda_bet.setText((0).toString())

                var define : TextView = findViewById(R.id.define_text)
                define.setText("Tie")
                return newIndex
            }
        }

        // Case: Banker gets Natural
        if(banker_score > 7){
            // banker wins
            if (player_score < banker_score){
                var total_chips : TextView = findViewById(R.id.chips_number)
                var bet : TextView = findViewById(R.id.bet_number)
                var banker_bet : TextView = findViewById(R.id.banker_number)
                var player_bet : TextView = findViewById(R.id.player_number)
                var dragon_bet : TextView = findViewById(R.id.dragon_number)
                var tie_bet : TextView = findViewById(R.id.tie_number)
                var panda_bet : TextView = findViewById(R.id.panda_number)

                var banker_bet_int : Int = banker_bet.text.toString().toInt()
                var total_chips_int :Int = total_chips.text.toString().toInt()
                total_chips_int = total_chips_int + banker_bet_int * 2
                total_chips.setText(total_chips_int.toString())

                bet.setText((0).toString())
                banker_bet.setText((0).toString())
                player_bet.setText((0).toString())
                dragon_bet.setText((0).toString())
                tie_bet.setText((0).toString())
                panda_bet.setText((0).toString())

                var define : TextView = findViewById(R.id.define_text)
                define.setText("Banker wins")
                return newIndex
            }
        }

        var player_draw = false
        var banker_draw = false

        var player_third = 0

        // If Player less then 6, Player draws a card
        if(player_score < 6){
            player_third = player_draw_fun(player_score,newIndex,numbers)
            val new_player_score : TextView= findViewById(R.id.player_score)
            player_score = new_player_score.text.toString().toInt()
            player_draw = true
            newIndex++;
        }

        // Banker draws a card if and only if the case below
        if(player_draw == false && banker_score < 6){
            banker_draw_fun(banker_score,newIndex,numbers)
            val new_banker_score : TextView= findViewById(R.id.banker_score)
            banker_score = new_banker_score.text.toString().toInt()
            banker_draw = true
            newIndex++;
        }

        if(banker_score < 3 && banker_draw == false){
            banker_draw_fun(banker_score,newIndex,numbers)
            val new_banker_score : TextView= findViewById(R.id.banker_score)
            banker_score = new_banker_score.text.toString().toInt()
            banker_draw = true
            newIndex++;
        }

        if(banker_score == 3 && player_third != 8 && banker_draw == false){
            banker_draw_fun(banker_score,newIndex,numbers)
            val new_banker_score : TextView= findViewById(R.id.banker_score)
            banker_score = new_banker_score.text.toString().toInt()
            banker_draw = true
            newIndex++;
        }
        if(banker_score == 4 && player_third < 8 && player_third > 1 && banker_draw == false){
            banker_draw_fun(banker_score,newIndex,numbers)
            val new_banker_score : TextView= findViewById(R.id.banker_score)
            banker_score = new_banker_score.text.toString().toInt()
            banker_draw = true
            newIndex++;
        }
        if(banker_score == 5 && player_third < 8 && player_third > 3 && banker_draw == false){
            banker_draw_fun(banker_score,newIndex,numbers)
            val new_banker_score : TextView= findViewById(R.id.banker_score)
            banker_score = new_banker_score.text.toString().toInt()
            banker_draw = true
            newIndex++;
        }
        if(banker_score == 6 && (player_third == 6 || player_third == 7) && banker_draw == false){
            banker_draw_fun(banker_score,newIndex,numbers)
            val new_banker_score : TextView= findViewById(R.id.banker_score)
            banker_score = new_banker_score.text.toString().toInt()
            banker_draw = true
            newIndex++;
        }

        // Result: Dragon
        if(banker_score == 7 && banker_draw == true && banker_score > player_score)
        {
            var total_chips : TextView = findViewById(R.id.chips_number)
            var bet : TextView = findViewById(R.id.bet_number)
            var banker_bet : TextView = findViewById(R.id.banker_number)
            var player_bet : TextView = findViewById(R.id.player_number)
            var dragon_bet : TextView = findViewById(R.id.dragon_number)
            var tie_bet : TextView = findViewById(R.id.tie_number)
            var panda_bet : TextView = findViewById(R.id.panda_number)

            var total_chips_int :Int = total_chips.text.toString().toInt()
            var banker_bet_int : Int = banker_bet.text.toString().toInt()
            var dragon_bet_int : Int = dragon_bet.text.toString().toInt()

            total_chips_int = total_chips_int + banker_bet_int + dragon_bet_int * 40
            total_chips.setText(total_chips_int.toString())

            bet.setText((0).toString())
            banker_bet.setText((0).toString())
            player_bet.setText((0).toString())
            dragon_bet.setText((0).toString())
            tie_bet.setText((0).toString())
            panda_bet.setText((0).toString())

            var define : TextView = findViewById(R.id.define_text)
            define.setText("Dragon !!!!!!!!!")

            return newIndex
        }

        // Result: Panda
        if(player_score == 8 && player_draw == true && player_score > banker_score){

            var total_chips : TextView = findViewById(R.id.chips_number)
            var bet : TextView = findViewById(R.id.bet_number)
            var banker_bet : TextView = findViewById(R.id.banker_number)
            var player_bet : TextView = findViewById(R.id.player_number)
            var dragon_bet : TextView = findViewById(R.id.dragon_number)
            var tie_bet : TextView = findViewById(R.id.tie_number)
            var panda_bet : TextView = findViewById(R.id.panda_number)

            var total_chips_int :Int = total_chips.text.toString().toInt()
            var panda_bet_int : Int = panda_bet.text.toString().toInt()
            var player_bet_int : Int = player_bet.text.toString().toInt()

            total_chips_int = total_chips_int + player_bet_int + 25 * panda_bet_int
            total_chips.setText(total_chips_int.toString())

            bet.setText((0).toString())
            banker_bet.setText((0).toString())
            player_bet.setText((0).toString())
            dragon_bet.setText((0).toString())
            tie_bet.setText((0).toString())
            panda_bet.setText((0).toString())

            var define : TextView = findViewById(R.id.define_text)
            define.setText("Panda !!!!!!!!")
            return newIndex
        }

        // Result: Tie
        if(player_score == banker_score){
            var total_chips : TextView = findViewById(R.id.chips_number)
            var bet : TextView = findViewById(R.id.bet_number)
            var banker_bet : TextView = findViewById(R.id.banker_number)
            var player_bet : TextView = findViewById(R.id.player_number)
            var dragon_bet : TextView = findViewById(R.id.dragon_number)
            var tie_bet : TextView = findViewById(R.id.tie_number)
            var panda_bet : TextView = findViewById(R.id.panda_number)

            var tie_bet_int : Int = tie_bet.text.toString().toInt()
            var total_chips_int :Int = total_chips.text.toString().toInt()
            var banker_bet_int : Int = banker_bet.text.toString().toInt()
            var player_bet_int : Int = player_bet.text.toString().toInt()

            total_chips_int = total_chips_int + tie_bet_int * 8 + banker_bet_int + player_bet_int
            total_chips.setText(total_chips_int.toString())

            bet.setText((0).toString())
            banker_bet.setText((0).toString())
            player_bet.setText((0).toString())
            dragon_bet.setText((0).toString())
            tie_bet.setText((0).toString())
            panda_bet.setText((0).toString())

            var define : TextView = findViewById(R.id.define_text)
            define.setText("Tie")
        }
        // Result: Banker Win
        else if(banker_score > player_score){
            var total_chips : TextView = findViewById(R.id.chips_number)
            var bet : TextView = findViewById(R.id.bet_number)
            var banker_bet : TextView = findViewById(R.id.banker_number)
            var player_bet : TextView = findViewById(R.id.player_number)
            var dragon_bet : TextView = findViewById(R.id.dragon_number)
            var tie_bet : TextView = findViewById(R.id.tie_number)
            var panda_bet : TextView = findViewById(R.id.panda_number)

            var total_chips_int :Int = total_chips.text.toString().toInt()
            var banker_bet_int : Int = banker_bet.text.toString().toInt()

            total_chips_int = total_chips_int + banker_bet_int * 2
            total_chips.setText(total_chips_int.toString())

            bet.setText((0).toString())
            banker_bet.setText((0).toString())
            player_bet.setText((0).toString())
            dragon_bet.setText((0).toString())
            tie_bet.setText((0).toString())
            panda_bet.setText((0).toString())

            var define : TextView = findViewById(R.id.define_text)
            define.setText("Bankers wins")
        }
        // Result: Player Win
        else{
            var total_chips : TextView = findViewById(R.id.chips_number)
            var bet : TextView = findViewById(R.id.bet_number)
            var banker_bet : TextView = findViewById(R.id.banker_number)
            var player_bet : TextView = findViewById(R.id.player_number)
            var dragon_bet : TextView = findViewById(R.id.dragon_number)
            var tie_bet : TextView = findViewById(R.id.tie_number)
            var panda_bet : TextView = findViewById(R.id.panda_number)

            var total_chips_int :Int = total_chips.text.toString().toInt()
            var player_bet_int : Int = player_bet.text.toString().toInt()

            total_chips_int = total_chips_int + player_bet_int * 2
            total_chips.setText(total_chips_int.toString())

            bet.setText((0).toString())
            banker_bet.setText((0).toString())
            player_bet.setText((0).toString())
            dragon_bet.setText((0).toString())
            tie_bet.setText((0).toString())
            panda_bet.setText((0).toString())

            var define : TextView = findViewById(R.id.define_text)
            define.setText("Player wins")
        }
        return newIndex
    }

    // function to reset the content in the app everytime the game finished in one round
    fun reset(index: Int)
    {
        val banker_number : TextView = findViewById(R.id.banker_number)
        val player_number : TextView = findViewById(R.id.player_number)
        val dragon_number : TextView = findViewById(R.id.dragon_number)
        val tie_number : TextView = findViewById(R.id.tie_number)
        val panda_number : TextView = findViewById(R.id.panda_number)

        banker_number.setText("")
        player_number.setText("")
        dragon_number.setText("")
        tie_number.setText("")
        panda_number.setText("")
        banker_number.isEnabled = true;
        player_number.isEnabled = true;
        dragon_number.isEnabled = true;
        tie_number.isEnabled = true;
        panda_number.isEnabled = true;

        val start_button = findViewById(R.id.start_button) as Button
        val clearbet_button = findViewById(R.id.clearbet_button) as Button
        val bet_button = findViewById(R.id.bet_button) as Button

        start_button.isClickable = true
        clearbet_button.isClickable = true
        bet_button.isClickable = true

        val banker_image1 : ImageView = findViewById(R.id.banker_image_1)
        val banker_image2 : ImageView = findViewById(R.id.banker_image_2)
        val player_image1 : ImageView = findViewById(R.id.player_image_1)
        val player_image2 : ImageView = findViewById(R.id.player_image_2)

        banker_image1.isClickable = false
        banker_image2.isClickable = false
        player_image1.isClickable = false
        player_image2.isClickable = false

        if(index + 6 > 100){
            start_button.isClickable == false
            var define : TextView = findViewById(R.id.define_text)
            define.setText("Decks done, Click Restart to continue")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.user_interface)

        // place two decks of cards
        var numbers = arrayOf<Card>(Card(R.drawable.c1, 1), Card(R.drawable.c2, 2),
            Card(R.drawable.c3, 3),Card(R.drawable.c4, 4), Card(R.drawable.c5,5),
            Card(R.drawable.c6, 6),Card(R.drawable.c7,7),Card(R.drawable.c8, 8),
            Card(R.drawable.c9,9), Card(R.drawable.c10,0),Card(R.drawable.c11,0),
            Card(R.drawable.c12,0),Card(R.drawable.c13,0),

            Card(R.drawable.s1,1),Card(R.drawable.s2,2),Card(R.drawable.s3,3),
            Card(R.drawable.s4,4),Card(R.drawable.s5,5),Card(R.drawable.s6,6),
            Card(R.drawable.s7,7),Card(R.drawable.s8,8),Card(R.drawable.s9,9),
            Card(R.drawable.s10,0),Card(R.drawable.s11,0),Card(R.drawable.s12,0),
            Card(R.drawable.s13,0),

            Card(R.drawable.d1,1),Card(R.drawable.d2,2),Card(R.drawable.d3,3),
            Card(R.drawable.d4,4),Card(R.drawable.d5,5),Card(R.drawable.d6,6),
            Card(R.drawable.d7,7),Card(R.drawable.d8,8),Card(R.drawable.d9,9),
            Card(R.drawable.d10,0),Card(R.drawable.d11,0),Card(R.drawable.d12,0),
            Card(R.drawable.d13,0),

            Card(R.drawable.h1,1),Card(R.drawable.h2,2),Card(R.drawable.h3,3),
            Card(R.drawable.h4,4),Card(R.drawable.h5,5),Card(R.drawable.h6,6),
            Card(R.drawable.h7,7),Card(R.drawable.h8,8),Card(R.drawable.h9,9),
            Card(R.drawable.h10,0),Card(R.drawable.h11,0),Card(R.drawable.h12,0),
            Card(R.drawable.h13,0),

            Card(R.drawable.c1, 1), Card(R.drawable.c2, 2),
            Card(R.drawable.c3, 3),Card(R.drawable.c4, 4), Card(R.drawable.c5,5),
            Card(R.drawable.c6, 6),Card(R.drawable.c7,7),Card(R.drawable.c8, 8),
            Card(R.drawable.c9,9), Card(R.drawable.c10,0),Card(R.drawable.c11,0),
            Card(R.drawable.c12,0),Card(R.drawable.c13,0),

            Card(R.drawable.s1,1),Card(R.drawable.s2,2),Card(R.drawable.s3,3),
            Card(R.drawable.s4,4),Card(R.drawable.s5,5),Card(R.drawable.s6,6),
            Card(R.drawable.s7,7),Card(R.drawable.s8,8),Card(R.drawable.s9,9),
            Card(R.drawable.s10,0),Card(R.drawable.s11,0),Card(R.drawable.s12,0),
            Card(R.drawable.s13,0),

            Card(R.drawable.d1,1),Card(R.drawable.d2,2),Card(R.drawable.d3,3),
            Card(R.drawable.d4,4),Card(R.drawable.d5,5),Card(R.drawable.d6,6),
            Card(R.drawable.d7,7),Card(R.drawable.d8,8),Card(R.drawable.d9,9),
            Card(R.drawable.d10,0),Card(R.drawable.d11,0),Card(R.drawable.d12,0),
            Card(R.drawable.d13,0),

            Card(R.drawable.h1,1),Card(R.drawable.h2,2),Card(R.drawable.h3,3),
            Card(R.drawable.h4,4),Card(R.drawable.h5,5),Card(R.drawable.h6,6),
            Card(R.drawable.h7,7),Card(R.drawable.h8,8),Card(R.drawable.h9,9),
            Card(R.drawable.h10,0),Card(R.drawable.h11,0),Card(R.drawable.h12,0),
            Card(R.drawable.h13,0),
            )
        numbers.shuffle()


        var index = 0;
        var banker_score = 0;
        var player_score = 0;

        var chips : Int = 300
        val chips_number : TextView = findViewById(R.id.chips_number)
        chips_number.setText(chips.toString())

        var bet : Int = 0
        val bet_number : TextView = findViewById(R.id.bet_number)

        val banker_number : TextView = findViewById(R.id.banker_number)
        val player_number : TextView = findViewById(R.id.player_number)
        val dragon_number : TextView = findViewById(R.id.dragon_number)
        val tie_number : TextView = findViewById(R.id.tie_number)
        val panda_number : TextView = findViewById(R.id.panda_number)

        val rule_button = findViewById(R.id.rule_button) as Button

        // Rule Button hander
        rule_button.setOnClickListener {
            val intent = Intent(this, RuleActivity::class.java).apply{
                putExtra("", 0)
            }
            //intent.putExtra("ElementId", -1)
            startActivity(intent)
        }

        // Restart button handler
        val restart_button = findViewById(R.id.restart_button) as Button
        restart_button.setOnClickListener {
            numbers.shuffle()
            index = 0
            reset(index)
            var define : TextView = findViewById(R.id.define_text)
            define.setText("Restarted, decks was reshuffled")
        }

        var banker_image1_clickable = true
        var banker_image2_clickable = true
        var player_image1_clickable = true
        var player_image2_clickable = true


        val start_button = findViewById(R.id.start_button) as Button
        val clearbet_button = findViewById(R.id.clearbet_button) as Button
        val bet_button = findViewById(R.id.bet_button) as Button

        // start button handler
        start_button.setOnClickListener {

            if(banker_number.text.toString() == ""){
                banker_number.setText((0).toString())
            }
            if(player_number.text.toString() == ""){
                player_number.setText((0).toString())
            }
            if(dragon_number.text.toString() == ""){
                dragon_number.setText((0).toString())
            }
            if(tie_number.text.toString() == ""){
                tie_number.setText((0).toString())
            }
            if(panda_number.text.toString() == ""){
                panda_number.setText((0).toString())
            }

            banker_number.isEnabled = false
            player_number.isEnabled = false
            dragon_number.isEnabled = false
            tie_number.isEnabled = false
            panda_number.isEnabled = false

            val banker_image1 : ImageView = findViewById(R.id.banker_image_1)
            banker_image1.setImageResource(R.drawable.blue_back)
            val banker_image2 : ImageView = findViewById(R.id.banker_image_2)
            banker_image2.setImageResource(R.drawable.blue_back)
            val banker_image3 : ImageView = findViewById(R.id.banker_image_3)
            banker_image3.setImageResource(R.drawable.blue_back)
            banker_image3.visibility = View.INVISIBLE


            val player_image1 : ImageView = findViewById(R.id.player_image_1)
            player_image1.setImageResource(R.drawable.blue_back)
            val player_image2 : ImageView = findViewById(R.id.player_image_2)
            player_image2.setImageResource(R.drawable.blue_back)
            val player_image3 : ImageView = findViewById(R.id.player_image_3)
            player_image3.setImageResource(R.drawable.blue_back)
            player_image3.visibility = View.INVISIBLE

            banker_image1.isClickable = true
            banker_image2.isClickable = true
            player_image1.isClickable = true
            player_image2.isClickable = true

            banker_image1_clickable = true
            banker_image2_clickable = true
            player_image1_clickable = true
            player_image2_clickable = true

            banker_score = 0
            val banker_text: TextView = findViewById(R.id.banker_score)
            banker_text.setText((0).toString())

            player_score = 0
            val player_text: TextView = findViewById(R.id.player_score)
            player_text.setText((0).toString())

            var define : TextView = findViewById(R.id.define_text)
            define.setText("Click a card")

            start_button.isClickable = false
            clearbet_button.isClickable = false
            bet_button.isClickable = false


            // Clickable image handler
            banker_image1.setOnClickListener {
                if(banker_image1_clickable == true) {
                    banker_image1.setImageResource(numbers[index].image)
                    banker_score += numbers[index].value
                    banker_score = banker_score % 10
                    banker_text.setText(banker_score.toString())
                    banker_image1_clickable = false
                }
                if(banker_image1_clickable == false && banker_image2_clickable == false &&
                    player_image1_clickable == false && player_image2_clickable == false) {
                        index = index + 4
                        index = calculationhelper(banker_score, player_score, index, numbers)
                        reset(index)
                }
            }

            banker_image2.setOnClickListener {
                if(banker_image2_clickable == true) {
                    banker_image2.setImageResource(numbers[index + 1].image)
                    banker_score += numbers[index + 1].value
                    banker_score = banker_score % 10
                    banker_text.setText(banker_score.toString())
                    banker_image2_clickable = false
                }
                if(banker_image1_clickable == false && banker_image2_clickable == false &&
                    player_image1_clickable == false && player_image2_clickable == false) {
                        index = index + 4
                        index = calculationhelper(banker_score, player_score, index, numbers)
                        reset(index)
                }
            }

            player_image1.setOnClickListener {
                if(player_image1_clickable == true) {
                    player_image1.setImageResource(numbers[index + 2].image)
                    player_score += numbers[index + 2].value
                    player_score = player_score % 10
                    player_text.setText(player_score.toString())
                    player_image1_clickable = false
                }
                if(banker_image1_clickable == false && banker_image2_clickable == false &&
                    player_image1_clickable == false && player_image2_clickable == false) {
                        index = index + 4
                        index = calculationhelper(banker_score, player_score, index, numbers)
                        reset(index)
                }
            }

            player_image2.setOnClickListener {
                if(player_image2_clickable == true) {
                    player_image2.setImageResource(numbers[index + 3].image)
                    player_score += numbers[index + 3].value
                    player_score = player_score % 10
                    player_text.setText(player_score.toString())
                    player_image2_clickable = false
                }
                if(banker_image1_clickable == false && banker_image2_clickable == false &&
                    player_image1_clickable == false && player_image2_clickable == false) {
                        index = index + 4
                        index = calculationhelper(banker_score, player_score, index, numbers)
                        reset(index)
                }
            }
        }

        // clear bet button handler
        clearbet_button.setOnClickListener {
            chips = chips_number.text.toString().toInt()
            bet = bet_number.text.toString().toInt()
            chips = chips + bet
            chips_number.setText((chips).toString())
            bet = 0
            bet_number.setText((0).toString())
            banker_number.setText("")
            player_number.setText("")
            dragon_number.setText("")
            tie_number.setText("")
            panda_number.setText("")
            banker_number.isEnabled = true;
            player_number.isEnabled = true;
            dragon_number.isEnabled = true;
            tie_number.isEnabled = true;
            panda_number.isEnabled = true;
        }

        // bet button handler
        bet_button.setOnClickListener {
            bet = bet_number.text.toString().toInt()
            chips = chips_number.text.toString().toInt()

            var banker : Int = 0
            var player : Int = 0
            var dragon : Int = 0
            var tie : Int = 0
            var panda : Int = 0


            if (banker_number.isEnabled == true && banker_number.text.toString() != "")
                banker = banker_number.text.toString().toInt()

            if (player_number.isEnabled == true && player_number.text.toString() != "")
                player = player_number.text.toString().toInt()

            if (dragon_number.isEnabled == true && dragon_number.text.toString() != "")
                dragon = dragon_number.text.toString().toInt()

            if (tie_number.isEnabled == true && tie_number.text.toString() != "")
                tie = tie_number.text.toString().toInt()

            if (panda_number.isEnabled == true && panda_number.text.toString() != "")
                panda = panda_number.text.toString().toInt()


            if(chips >= (banker + player + dragon + tie + panda)) {
                bet = bet + banker + player + dragon + tie + panda
                chips = chips - (banker + player + dragon + tie + panda)
                bet_number.setText(bet.toString())
                chips_number.setText(chips.toString())

                if(banker != 0) {
                    banker_number.isEnabled = false
                }
                if(player != 0) {
                    player_number.isEnabled = false
                }
                if(dragon != 0) {
                    dragon_number.isEnabled = false
                }
                if(tie != 0) {
                    tie_number.isEnabled = false
                }
                if(panda != 0) {
                    panda_number.isEnabled = false
                }
            }
        }
    }
}