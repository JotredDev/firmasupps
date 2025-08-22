
import os

# Preparing the directory paths for data and assets
base_path = os.path.abspath(os.path.join(os.path.dirname( __file__ ), '..', 'src'))
assets_path = base_path + "\\main\\resources\\assets\\firmasupps\\"

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


generic_candle_holder = """{
  "variants": {
    "lit=false,facing=north,face=floor,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_floor_1"
    },
    "lit=false,facing=north,face=floor,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_floor_2"
    },
    "lit=false,facing=north,face=floor,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_floor_3"
    },
    "lit=false,facing=north,face=floor,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_floor_4"
    },
    "lit=false,facing=north,face=wall,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_wall_1"
    },
    "lit=false,facing=north,face=wall,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_wall_2"
    },
    "lit=false,facing=north,face=wall,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_wall_3"
    },
    "lit=false,facing=north,face=wall,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_wall_4"
    },
    "lit=false,facing=north,face=ceiling,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_1"
    },
    "lit=false,facing=north,face=ceiling,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_2"
    },
    "lit=false,facing=north,face=ceiling,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_3"
    },
    "lit=false,facing=north,face=ceiling,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_4"
    },
    "lit=false,facing=east,face=floor,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_floor_1",
      "y": 90
    },
    "lit=false,facing=east,face=floor,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_floor_2",
      "y": 90
    },
    "lit=false,facing=east,face=floor,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_floor_3",
      "y": 90
    },
    "lit=false,facing=east,face=floor,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_floor_4",
      "y": 90
    },
    "lit=false,facing=east,face=wall,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_wall_1",
      "y": 90
    },
    "lit=false,facing=east,face=wall,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_wall_2",
      "y": 90
    },
    "lit=false,facing=east,face=wall,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_wall_3",
      "y": 90
    },
    "lit=false,facing=east,face=wall,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_wall_4",
      "y": 90
    },
    "lit=false,facing=east,face=ceiling,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_1f",
      "y": 90
    },
    "lit=false,facing=east,face=ceiling,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_2f",
      "y": 90
    },
    "lit=false,facing=east,face=ceiling,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_3f",
      "y": 90
    },
    "lit=false,facing=east,face=ceiling,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_4",
      "y": 90
    },
    "lit=false,facing=south,face=floor,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_floor_1",
      "y": 180
    },
    "lit=false,facing=south,face=floor,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_floor_2",
      "y": 180
    },
    "lit=false,facing=south,face=floor,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_floor_3",
      "y": 180
    },
    "lit=false,facing=south,face=floor,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_floor_4",
      "y": 180
    },
    "lit=false,facing=south,face=wall,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_wall_1",
      "y": 180
    },
    "lit=false,facing=south,face=wall,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_wall_2",
      "y": 180
    },
    "lit=false,facing=south,face=wall,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_wall_3",
      "y": 180
    },
    "lit=false,facing=south,face=wall,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_wall_4",
      "y": 180
    },
    "lit=false,facing=south,face=ceiling,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_1",
      "y": 180
    },
    "lit=false,facing=south,face=ceiling,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_2",
      "y": 180
    },
    "lit=false,facing=south,face=ceiling,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_3",
      "y": 180
    },
    "lit=false,facing=south,face=ceiling,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_4",
      "y": 180
    },
    "lit=false,facing=west,face=floor,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_floor_1",
      "y": 270
    },
    "lit=false,facing=west,face=floor,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_floor_2",
      "y": 270
    },
    "lit=false,facing=west,face=floor,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_floor_3",
      "y": 270
    },
    "lit=false,facing=west,face=floor,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_floor_4",
      "y": 270
    },
    "lit=false,facing=west,face=wall,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_wall_1",
      "y": 270
    },
    "lit=false,facing=west,face=wall,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_wall_2",
      "y": 270
    },
    "lit=false,facing=west,face=wall,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_wall_3",
      "y": 270
    },
    "lit=false,facing=west,face=wall,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_wall_4",
      "y": 270
    },
    "lit=false,facing=west,face=ceiling,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_1f",
      "y": 270
    },
    "lit=false,facing=west,face=ceiling,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_2f",
      "y": 270
    },
    "lit=false,facing=west,face=ceiling,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_3f",
      "y": 270
    },
    "lit=false,facing=west,face=ceiling,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_4",
      "y": 270
    },
    "lit=true,facing=north,face=floor,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_floor_1_lit"
    },
    "lit=true,facing=north,face=floor,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_floor_2_lit"
    },
    "lit=true,facing=north,face=floor,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_floor_3_lit"
    },
    "lit=true,facing=north,face=floor,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_floor_4_lit"
    },
    "lit=true,facing=north,face=wall,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_wall_1_lit"
    },
    "lit=true,facing=north,face=wall,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_wall_2_lit"
    },
    "lit=true,facing=north,face=wall,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_wall_3_lit"
    },
    "lit=true,facing=north,face=wall,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_wall_4_lit"
    },
    "lit=true,facing=north,face=ceiling,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_1_lit"
    },
    "lit=true,facing=north,face=ceiling,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_2_lit"
    },
    "lit=true,facing=north,face=ceiling,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_3_lit"
    },
    "lit=true,facing=north,face=ceiling,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_4_lit"
    },
    "lit=true,facing=east,face=floor,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_floor_1_lit",
      "y": 90
    },
    "lit=true,facing=east,face=floor,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_floor_2_lit",
      "y": 90
    },
    "lit=true,facing=east,face=floor,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_floor_3_lit",
      "y": 90
    },
    "lit=true,facing=east,face=floor,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_floor_4_lit",
      "y": 90
    },
    "lit=true,facing=east,face=wall,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_wall_1_lit",
      "y": 90
    },
    "lit=true,facing=east,face=wall,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_wall_2_lit",
      "y": 90
    },
    "lit=true,facing=east,face=wall,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_wall_3_lit",
      "y": 90
    },
    "lit=true,facing=east,face=wall,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_wall_4_lit",
      "y": 90
    },
    "lit=true,facing=east,face=ceiling,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_1f_lit",
      "y": 90
    },
    "lit=true,facing=east,face=ceiling,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_2f_lit",
      "y": 90
    },
    "lit=true,facing=east,face=ceiling,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_3f_lit",
      "y": 90
    },
    "lit=true,facing=east,face=ceiling,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_4_lit",
      "y": 90
    },
    "lit=true,facing=south,face=floor,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_floor_1_lit",
      "y": 180
    },
    "lit=true,facing=south,face=floor,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_floor_2_lit",
      "y": 180
    },
    "lit=true,facing=south,face=floor,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_floor_3_lit",
      "y": 180
    },
    "lit=true,facing=south,face=floor,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_floor_4_lit",
      "y": 180
    },
    "lit=true,facing=south,face=wall,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_wall_1_lit",
      "y": 180
    },
    "lit=true,facing=south,face=wall,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_wall_2_lit",
      "y": 180
    },
    "lit=true,facing=south,face=wall,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_wall_3_lit",
      "y": 180
    },
    "lit=true,facing=south,face=wall,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_wall_4_lit",
      "y": 180
    },
    "lit=true,facing=south,face=ceiling,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_1_lit",
      "y": 180
    },
    "lit=true,facing=south,face=ceiling,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_2_lit",
      "y": 180
    },
    "lit=true,facing=south,face=ceiling,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_3_lit",
      "y": 180
    },
    "lit=true,facing=south,face=ceiling,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_4_lit",
      "y": 180
    },
    "lit=true,facing=west,face=floor,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_floor_1_lit",
      "y": 270
    },
    "lit=true,facing=west,face=floor,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_floor_2_lit",
      "y": 270
    },
    "lit=true,facing=west,face=floor,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_floor_3_lit",
      "y": 270
    },
    "lit=true,facing=west,face=floor,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_floor_4_lit",
      "y": 270
    },
    "lit=true,facing=west,face=wall,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_wall_1_lit",
      "y": 270
    },
    "lit=true,facing=west,face=wall,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_wall_2_lit",
      "y": 270
    },
    "lit=true,facing=west,face=wall,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_wall_3_lit",
      "y": 270
    },
    "lit=true,facing=west,face=wall,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_wall_4_lit",
      "y": 270
    },
    "lit=true,facing=west,face=ceiling,candles=1": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_1f_lit",
      "y": 270
    },
    "lit=true,facing=west,face=ceiling,candles=2": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_2f_lit",
      "y": 270
    },
    "lit=true,facing=west,face=ceiling,candles=3": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_3f_lit",
      "y": 270
    },
    "lit=true,facing=west,face=ceiling,candles=4": {
      "model": "supplementaries:block/candle_holders/%s_ceiling_4_lit",
      "y": 270
    }
  }
}
"""


generic_item_model = """{
	"parent": "item/generated",
	"textures": {
		"layer0": "supplementaries:item/%s"
	}
}
"""


def write_candle_holder_blockstates () :

    candle_holder_path = assets_path + "blockstates\\candle_holder\\"

    if not os.path.exists(candle_holder_path) :

        os.makedirs(candle_holder_path)

    for color in colors :

        color_array = (color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       color,
                       )

        compat_file = open(candle_holder_path + "%s.json" % color, "wt")
        compat_file.write(generic_candle_holder % color_array)
        compat_file.close()


def write_candle_holder_item_models() :

    item_model_path = assets_path + "models\\item\\candle_holder\\"

    if not os.path.exists(item_model_path) :

        os.makedirs(item_model_path)

    for color in colors :

        item_file = open(item_model_path + "%s.json" % color, "wt")
        item_file.write(generic_item_model % ("candle_holders/%s" % color))
        item_file.close()


def write_sconce_item_models() :
    
    item_model_path = assets_path + "models\\item\\"

    if not os.path.exists(item_model_path) :

        os.makedirs(item_model_path)

    item_file = open(item_model_path + "sconce.json", "wt")
    item_file.write(generic_item_model % ("sconce"))
    item_file.close()
    
    item_file = open(item_model_path + "soul_sconce.json", "wt")
    item_file.write(generic_item_model % ("sconce_soul"))
    item_file.close()






def write_all () :

    write_candle_holder_blockstates()
    write_candle_holder_item_models()
    write_sconce_item_models()


if __name__ == "__main__" :

    write_all()
