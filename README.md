# ✨🎇RealFireworks🎆✨
A minecraft plugin that makes fireworks just a bit better.

## Usage✅
```
/realfireworks         | Open fireworks menu | Permission: realfireworks.use
/realfireworks reload  | reload all configs  | Permission: realfireworks.reload
```

## Config⚙️
### Config.yml
```yaml
Debug: false          # Debug messages
FireworkDamage: false # Enable or disable firework damage from this plugin
FixWarnings: false    # Automatically tries to fix warnings
```
### Firework Types
```
Rocket
Ground
Cake
Fountain > not implemented
```
### Firework Effect Types
```
BALL
BALL_LARGE
STAR
BURST
CREEPER
```
### Extra Firework Effects
```
Flicker | Adds a flicker/crackling effect to the firework
Trail   | Adds a trail effect to the firework
Fade    | Adds a fade effect to the firework (Use FadeRed FadeGreen FadeBlue)
Smoke   | Adds a smoke effect to the firework (Use SmokeIntensity and SmokeSize)
```
### Examples
<details>
  <summary>Ground</summary>

  ```YAML
example-ground:
  Name: '&cExample Firework'
  Lore:
    - Right click to use
  FireworkType: ground
  FireworkEffects:
    Power: 2
    Smoke: false
    Type: BALL
    Red: 20
    Green: 81
    Blue: 179
    Flicker: true
    Trail: false
    Fade: false
  ```
</details>

<details>
  <summary>Rocket</summary>

  ```YAML
example-rocket:
  Name: '&cExample Rocket'
  Lore:
    - Right click to use
  FireworkType: rocket
  FireworkEffects:
    Power: 1
    Smoke: true
    SmokeIntensity: 1
    SmokeSize: 2
    Type: BALL_LARGE
    Red: 20
    Green: 81
    Blue: 179
    Flicker: true
    Trail: false
    Fade: false
  ```
</details>

<details>
  <summary>Cake</summary>

  ```YAML
example-cake:
  Name: '&cExample Cake'
  Lore:
    - Right click to use
  FireworkType: cake
  CakeEffects:
    '1':
      Delay: 5
      FireworkEffects:
        Type: BALL
        Smoke: false
        Red: 20
        Green: 81
        Blue: 179
        Flicker: true
        Trail: false
        Fade: true
        FadeRed: 255
        FadeGreen: 255
        FadeBlue: 255
        Power: 1
    '2':
      Delay: 20
      FireworkEffects:
        Power: 2
        Smoke: false
        Type: BURST
        Red: 20
        Green: 81
        Blue: 179
        Flicker: true
        Trail: false
        Fade: true
        FadeRed: 255
        FadeGreen: 255
        FadeBlue: 255
  ```
</details>


## Pro tip🤔💭
Just play around with the config, and you will get it.