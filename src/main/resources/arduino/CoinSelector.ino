const int coinPin = 23;
volatile byte coinPulse = 0;

unsigned long currentTime = 0;
unsigned long lastPulseTime = 0;
unsigned long debounceDelay = 100;
unsigned long signalTimeout = 200;

int numberOfPulses = 0;

void IRAM_ATTR coinInterrupt() {
  coinPulse = 1;
}

float getCoinValue(int numberOfPulses){
  switch (numberOfPulses) {
    case 1: return 0.10;
    case 2: return 0.20;
    case 3: return 0.50;
    case 4: return 1.00;
    case 5: return 2.00;
  }
}

void setup() {
  Serial.begin(9600);
  pinMode(coinPin, INPUT);
  attachInterrupt(digitalPinToInterrupt(coinPin), coinInterrupt, FALLING);
}

void loop() {
  currentTime = millis();
  if (coinPulse == 1) {
    coinPulse = 0;
    if (currentTime - lastPulseTime < debounceDelay)
      return;
    lastPulseTime = currentTime;
    numberOfPulses++;
  } else if (numberOfPulses > 0 && currentTime - lastPulseTime > signalTimeout) {
    Serial.println(getCoinValue(numberOfPulses));
    numberOfPulses = 0;
  }
  delay(1);
}
