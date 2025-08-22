
import os

# Preparing the directory paths for data and assets
base_path = os.path.abspath(os.path.join(os.path.dirname( __file__ ), '..', 'src'))
original_recipes_path = base_path + "\\main\\resources\\data\\supplementaries\\recipes\\"
compat_crafting_path = base_path + "\\main\\resources\\data\\firmasupps\\recipes\\crafting\\"
compat_barrel_path = base_path + "\\main\\resources\\data\\firmasupps\\recipes\\barrel\\dye\\"

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


disable_recipe = """{
  "conditions": [
    {
      "type": "forge:false"
    }
  ]
}
"""

generic_awning = """{
  "conditions": [
    {
      "type": "supplementaries:flag",
      "flag": "awning"
    }
  ],
  "type": "tfc:barrel_sealed",
  "input_item": {
    "ingredient": {
      "item": "supplementaries:awning"
    }
  },
  "input_fluid": {
    "ingredient": "tfc:%s_dye",
    "amount": 25
  },
  "output_item": {
    "item": "supplementaries:awning_%s"
  },
  "duration": 1000
}
"""

generic_bunting = """{
  "conditions": [
    {
      "type": "supplementaries:flag",
      "flag": "bunting"
    }
  ],
  "type": "tfc:barrel_sealed",
  "input_item": {
    "ingredient": {
      "item": "supplementaries:bunting"
    }
  },
  "input_fluid": {
    "ingredient": "tfc:%s_dye",
    "amount": 25
  },
  "output_item": {
    "item": "supplementaries:bunting",
    "nbt": {
      "Color": "%s"
    }
  },
  "duration": 1000
}
"""

generic_candle_holder = """{
  "conditions": [
    {
      "type": "supplementaries:flag",
      "flag": "candle_holder"
    }
  ],
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "0",
    "1"
  ],
  "key": {
    "0": {
      "item": "tfc:candle/%s"
    },
    "1": {
      "item": "tfc:metal/rod/steel"
    }
  },
  "result": {
    "item": "firmasupps:candle_holder/%s"
  }
}
"""

generic_candle_holder_dye = """{
  "conditions": [
    {
      "type": "supplementaries:flag",
      "flag": "candle_holder"
    }
  ],
  "type": "tfc:barrel_sealed",
  "input_item": {
    "ingredient": {
      "item": "firmasupps:candle_holder"
    }
  },
  "input_fluid": {
    "ingredient": "tfc:%s_dye",
    "amount": 25
  },
  "output_item": {
      "item": "firmasupps:candle_holder/%s"
  },
  "duration": 1000
}
"""

generic_flag = """{
  "conditions": [
    {
      "type": "supplementaries:flag",
      "flag": "flag"
    }
  ],
  "type": "tfc:barrel_sealed",
  "input_item": {
    "ingredient": {
      "item": "supplementaries:flag_white"
    }
  },
  "input_fluid": {
    "ingredient": "tfc:%s_dye",
    "amount": 25
  },
  "output_item": {
    "item": "supplementaries:flag_%s"
  },
  "duration": 1000
}
"""






def write_specific (path_ending : str, file_name : str, generic_str : str) :

    original_path = original_recipes_path + path_ending + "\\"
    compat_path = compat_crafting_path + path_ending + "\\"

    if not os.path.exists(original_path) :

        os.makedirs(original_path)
    
    
    if not os.path.exists(compat_path) :

        os.makedirs(compat_path)

    for color in colors :

        original_file = open(original_path + file_name % color, "wt")
        original_file.write(disable_recipe)
        original_file.close()
    
        compat_file = open(compat_path + file_name % color, "wt")
        compat_file.write(generic_str % (color, color))
        compat_file.close()


def write_disable_recipe (path_ending : str, file_name : str) :
    
    original_path = original_recipes_path + path_ending + "\\"

    if not os.path.exists(original_path) :

        os.makedirs(original_path)
    
    for color in colors :

        original_file = open(original_path + file_name % color, "wt")
        original_file.write(disable_recipe)
        original_file.close()

        
def write_specific_with_exception (path_ending : str, file_name : str, generic_str : str, exception : int, path=compat_barrel_path) :

    compat_path = path + path_ending + "\\"    
  
    if not os.path.exists(compat_path) :

        os.makedirs(compat_path)

    for i in range (len (colors)) :

        if i != exception :
            
            color = colors[i]
            
            compat_file = open(compat_path + file_name % color, "wt")
            compat_file.write(generic_str % (color, color))
            compat_file.close()


def write_all () :

    write_disable_recipe ("awnings", "awning_%s_2.json")
    write_disable_recipe ("bunting", "bunting_%s.json")
    write_disable_recipe ("bunting", "bunting_%s_2.json")
    write_disable_recipe ("flags", "flag_%s.json")
    write_disable_recipe ("candle_holders", "candle_holder_%s.json")
    write_disable_recipe ("candle_holders", "candle_holder_%s_dye.json")

    write_specific_with_exception ("flags", "%s_flag.json", generic_flag, 0)
    write_specific_with_exception ("awnings", "%s_awning.json", generic_awning, -1)
    write_specific_with_exception ("bunting", "%s_bunting.json", generic_bunting, 0)
    write_specific_with_exception ("candle_holders", "%s_candle_holder.json", generic_candle_holder_dye, -1)
    write_specific_with_exception ("candle_holders", "candle_holder_%s.json", generic_candle_holder, -1, compat_crafting_path)






if __name__ == "__main__" :

    write_all()


