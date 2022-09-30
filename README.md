# ✨🎇RealFireworks🎆✨
A minecraft plugin that makes fireworks just a bit better.

## Usage✅
```
/realfireworks         | Open fireworks menu
/realfireworks reload  | reload all configs
```

## Config⚙️
### Firework Types
```
Rocket
Ground
Cake
Fountain > not implemented
```
### Firework Effects
```
BALL
BALL_LARGE
STAR
BURST
CREEPER
```
### Examples
<details>
  <summary>Ground</summary>

  ```YAML
  example-ground:
    Name: "&cExample Firework"
    Lore:
      - "Right click to use"
    FireworkType: "ground"
    FireworkEffects:
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
    Name: "&cExample Firework"
    Lore:
      - "Right click to use"
    FireworkType: "rocket"
    FireworkEffects:
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
  <summary>Cake</summary>

  ```YAML
  example-cake:
    Name: "&cExample Cake"
    Lore:
      - "Right click to use"
    FireworkType: "cake"
    CakeEffects:
      1:
        Delay: 5
        FireworkEffects:
          Power: 2
          Type: BALL
          Red: 20
          Green: 81
          Blue: 179
          Flicker: true
          Trail: false
          Fade: true
          FadeRed: 255
          FadeGreen: 255
          FadeBlue: 255
      2:
        Delay: 20
        FireworkEffects:
          Power: 2
          Type: BALL
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
Just play around with the config and you will get it.