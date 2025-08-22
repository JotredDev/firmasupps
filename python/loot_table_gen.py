
import os

# Preparing the directory paths for data and assets
base_path = os.path.abspath(os.path.join(os.path.dirname( __file__ ), '..', 'src'))
loot_table_path = base_path + "\\main\\resources\\data\\firmasupps\\loot_tables\\blocks\\candle_holder\\"

colors = [
    "white",
    "light_gray",
    "gray",
    "black",
    "brown",
    "red",
    "orange",
    "yellow",
    "lime",
    "green",
    "cyan",
    "light_blue",
    "blue",
    "purple",
    "magenta",
    "pink"
]

generic_loot_table = """{
  "type": "minecraft:block",
  "pools": [
    {
      "name": "loot_pool",
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "firmasupps:candle_holder/%s",
          "conditions": [
            {
              "condition": "minecraft:block_state_property",
              "block": "firmasupps:candle_holder/%s",
              "properties": {
                "candles": "1"
              }
            }
          ],
          "functions": [
            {
              "function": "minecraft:set_count",
              "count": 1
            }
          ]
        }
      ],
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ]
    },
    {
      "name": "loot_pool",
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "firmasupps:candle_holder/%s",
          "conditions": [
            {
              "condition": "minecraft:block_state_property",
              "block": "firmasupps:candle_holder/%s",
              "properties": {
                "candles": "2"
              }
            }
          ],
          "functions": [
            {
              "function": "minecraft:set_count",
              "count": 2
            }
          ]
        }
      ],
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ]
    },
    {
      "name": "loot_pool",
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "firmasupps:candle_holder/%s",
          "conditions": [
            {
              "condition": "minecraft:block_state_property",
              "block": "firmasupps:candle_holder/%s",
              "properties": {
                "candles": "3"
              }
            }
          ],
          "functions": [
            {
              "function": "minecraft:set_count",
              "count": 3
            }
          ]
        }
      ],
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ]
    },
    {
      "name": "loot_pool",
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "firmasupps:candle_holder/%s",
          "conditions": [
            {
              "condition": "minecraft:block_state_property",
              "block": "firmasupps:candle_holder/%s",
              "properties": {
                "candles": "4"
              }
            }
          ],
          "functions": [
            {
              "function": "minecraft:set_count",
              "count": 4
            }
          ]
        }
      ],
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ]
    }
  ]
}
"""







def write_all () :

    if not os.path.exists(loot_table_path) :

        os.makedirs(loot_table_path)
    
    for color in colors :

        loot_table_file = open(loot_table_path + "%s.json" % color, "wt")
        loot_table_file.write(generic_loot_table % (color, color, color, color, color, color, color, color))
        loot_table_file.close()


if __name__ == "__main__" :

    write_all()
