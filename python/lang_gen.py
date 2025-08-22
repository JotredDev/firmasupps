
import os

# Preparing the directory paths for data and assets
base_path = os.path.abspath(os.path.join(os.path.dirname( __file__ ), '..', 'src'))
lang_path = base_path + "\\main\\resources\\assets\\firmasupps\\lang\\"

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

readable_colors = [
    "White",
    "Light Gray",
    "Gray",
    "Black",
    "Brown",
    "Red",
    "Orange",
    "Yellow",
    "Lime",
    "Green",
    "Cyan",
    "Light Blue",
    "Blue",
    "Purple",
    "Magenta",
    "Pink"
]

outlier_keys = [
    "config.jade.plugin_firmasupps.candle_holder",
    "config.jade.plugin_firmasupps.sconce",
    "config.jade.plugin_firmasupps.sconce_wall",
    "config.jade.plugin_firmasupps.sconce_lever",
    "config.jade.plugin_firmasupps.fire_pit",
    "firmasupps.creative_tab.firmasupps",
    "block.firmasupps.candle_holder",
    "block.firmasupps.sconce",
    "block.firmasupps.sconce_soul",
    "block.firmasupps.sconce_lever",
    "block.firmasupps.fire_pit",
    "block.firmasupps.pancake",
    "block.firmasupps.sack",
    "block.firmasupps.planter",
    "block.firmasupps.goblet",
    "firmasupps.block_entity.sack",
    "tfc.recipe.barrel.firmasupps.barrel.dye.awnings.bleach_awning",
    "tfc.recipe.barrel.firmasupps.barrel.dye.bunting.bleach_bunting",
    "tfc.recipe.barrel.firmasupps.barrel.dye.flags.bleach_flag",
    "tfc.recipe.barrel.firmasupps.barrel.dye.candle_holders.bleach_candle_holder"
]

outlier_values = [
    "Candle Holder",
    "Sconce",
    "Wall Sconce",
    "Sconce Lever",
    "Firepit",
    "Firmasupplementaries",
    "Candle Holder",
    "Sconce",
    "Soul Sconce",
    "Sconce Lever",
    "Fire Pit",
    "Pancake",
    "Sack",
    "Planter",
    "Goblet",
    "Sack",
    "Bleaching Awning",
    "Bleaching Bunting",
    "Bleaching Flag",
    "Bleaching Candle Holder"
]


def write_outliers(lang_file) :

    for i in range(len(outlier_keys)) :

        lang_file.write("  \"%s\": \"%s\",\n" % (outlier_keys[i], outlier_values[i]))


def write_colors(lang_file) :

    for i in range(len(colors)) :

        lang_file.write("  \"tfc.recipe.barrel.firmasupps.barrel.dye.awnings.%s_awning\": \"Dyeing Awning %s\",\n" % (colors[i], readable_colors[i]))
    
    for i in range(1, len(colors)) :

        lang_file.write("  \"tfc.recipe.barrel.firmasupps.barrel.dye.bunting.%s_bunting\": \"Dyeing Bunting %s\",\n" % (colors[i], readable_colors[i]))
        
    for i in range(1, len(colors)) :

        lang_file.write("  \"tfc.recipe.barrel.firmasupps.barrel.dye.flags.%s_flag\": \"Dyeing Flag %s\",\n" % (colors[i], readable_colors[i]))

    for i in range(len(colors)) :
        
        lang_file.write("  \"tfc.recipe.barrel.firmasupps.barrel.dye.candle_holder.%s_flag\": \"Dyeing Candle Holder %s\",\n" % (colors[i], readable_colors[i]))

    for i in range(len(colors) - 1) :
        
        lang_file.write("  \"block.firmasupps.candle_holder.%s\": \"%s Candle Holder\",\n" % (colors[i], readable_colors[i]))

    lang_file.write("  \"block.firmasupps.candle_holder.%s\": \"%s Candle Holder\"\n" % (colors[-1], readable_colors[-1]))








def write_all() :
    
    if not os.path.exists(lang_path) :

        os.makedirs(lang_path)

    lang_file = open(lang_path + "en_us.json", "wt")
    lang_file.write("{\n")

    write_outliers(lang_file)
    write_colors(lang_file)
    
    lang_file.write("}\n")
    lang_file.close()






if __name__ == "__main__" :

    write_all()
