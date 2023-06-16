#include <WiFi.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>

#include <SPI.h>
#include <MFRC522.h>

#define RST_PIN 22
#define SS_PIN 5

#define LED_PIN 21

MFRC522 mfrc522(SS_PIN, RST_PIN);

const char* ssid = "iPhone";
const char* password = "00000000";
String protocol = "http://";
String host = "172.20.10.3";
String port = "8080";
String server_url = protocol + host + ":" + port;

HTTPClient http_client;

void setup() {
        pinMode(LED_PIN, OUTPUT);
        digitalWrite(LED_PIN, LOW);
        setup_serial();
        setup_wifi();
        setup_mfrc();
}
void setup_serial() {
        Serial.begin(115200);
        delay(5000);
}

void setup_mfrc() {
        SPI.begin();
        mfrc522.PCD_Init();
        delay(500);
        mfrc522.PCD_DumpVersionToSerial();
        Serial.println(F("MFRC setup successfull!"));
}

void setup_wifi() {
        WiFi.begin(ssid, password);
        Serial.print("Connecting to wi-fi: ");
        Serial.println(ssid);
        while (WiFi.status() != WL_CONNECTED) {
                delay(100);
                Serial.print(".");
        }
        Serial.println();
        Serial.print("Connected to wi-fi: ");
        Serial.println(ssid);
}


void loop() {
        if (!mfrc522.PICC_IsNewCardPresent()) {
                return;
        }
        if (!mfrc522.PICC_ReadCardSerial()) {
                return;
        }

        Serial.print(F("Card UID:"));
        const String rfid_uid = get_rfid_uid_string(mfrc522.uid.uidByte, mfrc522.uid.size);
        Serial.println(rfid_uid);

        if (WL_CONNECTED == WiFi.status()) {
                send_rfid_uid(rfid_uid);
        }
        delay(500);
}

void send_rfid_uid(const String rfid_uid) {

        if (!http_client.begin(server_url + "/api/v1/visit")) {
                Serial.println("Could not start http client!");
        }
        http_client.addHeader("Content-Type", "application/json");
        DynamicJsonDocument visit(1024);
        visit["rfidUid"] = rfid_uid.toInt();
        String json;
        serializeJson(visit, json);
        int http_code = http_client.POST(json);
        if (http_code > 0) {
                Serial.println(http_code);
                if (200 == http_code) { // TODO: add enum with http status codes
                        digitalWrite(LED_PIN, HIGH);
                        delay(1000);
                        digitalWrite(LED_PIN, LOW);
                } else if (403 == http_code) {
                        digitalWrite(LED_PIN, LOW);
                }
        } else {
                Serial.println("Could not get response from server!");
        }
        http_client.end();
}

String get_rfid_uid_string(const byte* buffer, const byte bufferSize) {
        String result = "";
        for (byte i = 0; i < bufferSize; i++) {
                result += buffer[i];
        }
        return result;
}
