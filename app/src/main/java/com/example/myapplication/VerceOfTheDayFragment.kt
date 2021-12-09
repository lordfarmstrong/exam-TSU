package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class VerceOfTheDayFragment : Fragment() {

    lateinit var heading: TextView
    lateinit var showButton: Button
    lateinit var fieldResult: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_verce_of_the_day, container, false)

        showButton.setOnClickListener {
            val url: "https://favqs.com/api/qotd"
            GetURLData(url)

            //val link = Uri.parse("https://google.com/")
            //val intent = Intent(Intent.ACTION_VIEW, link)
            //context?.startActivity(intent)
        }
        protected override fun doInBackground(vararg strings: String): String? {
            var connection: HttpURLConnection? = null
            var reader: BufferedReader? = null
            try {
                // Создаем URL подключение, а также HTTP подключение
                val url = URL(strings[0])
                connection = url.openConnection() as HttpURLConnection
                connection.connect()

                // Создаем объекты для считывания данных из файла
                val stream = connection!!.inputStream
                reader = BufferedReader(InputStreamReader(stream))

                // Генерируемая строка
                val buffer = StringBuilder()
                var line: String? = ""

                // Считываем файл и записываем все в строку
                while (reader.readLine().also { line = it } != null) buffer.append(line)
                    .append("\n")

                // Возвращаем строку
                return buffer.toString()
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                // Закрываем соединения
                connection?.disconnect()
                try {
                    reader?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                return view
            }


        }
