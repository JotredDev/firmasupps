
import os

# Preparing the directory paths for data and assets
base_path = os.path.abspath(os.path.join(os.path.dirname( __file__ ), '..', 'src'))
mc_tag_path = base_path + "\\main\\resources\\data\\minecraft\\tags\\"
supp_tag_path = base_path + "\\main\\resources\\data\\supplementaries\\tags\\"
firmasupps_tag_path = base_path + "\\main\\resources\\data\\firmasupps\\tags\\"
create_tag_path = base_path + "\\main\\resources\\data\\create\\tags\\"

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


def write_candle_holders() :

    # firmasupps:candle_holders
    block_tag_file = open(firmasupps_tag_path + "blocks\\candle_holders.json", "wt")
    block_tag_file.write("{\n")
    block_tag_file.write("  \"replace\": false,\n")
    block_tag_file.write("  \"values\":\n")
    block_tag_file.write("  [\n")

    for color in colors :

        block_tag_file.write("    \"firmasupps:candle_holder/%s\",\n" % color)

    block_tag_file.write("    \"firmasupps:candle_holder\"\n")

    block_tag_file.write("  ]\n")
    block_tag_file.write("}\n")
    block_tag_file.close()

    item_tag_file = open(firmasupps_tag_path + "items\\candle_holders.json", "wt")
    item_tag_file.write("{\n")
    item_tag_file.write("  \"replace\": false,\n")
    item_tag_file.write("  \"values\":\n")
    item_tag_file.write("  [\n")

    for color in colors :

        item_tag_file.write("    \"firmasupps:candle_holder/%s\",\n" % color)

    item_tag_file.write("    \"firmasupps:candle_holder\"\n")

    item_tag_file.write("  ]\n")
    item_tag_file.write("}\n")
    item_tag_file.close()
    
    # adding to supplementaries:candle_holders tag
    block_tag_file = open(supp_tag_path + "blocks\\candle_holders.json", "wt")
    block_tag_file.write("{\n")
    block_tag_file.write("  \"replace\": false,\n")
    block_tag_file.write("  \"values\":\n")
    block_tag_file.write("  [\n")
    block_tag_file.write("    \"#firmasupps:candle_holders\"\n")
    block_tag_file.write("  ]\n")
    block_tag_file.write("}\n")
    block_tag_file.close()
    
    item_tag_file = open(supp_tag_path + "items\\candle_holders.json", "wt")
    item_tag_file.write("{\n")
    item_tag_file.write("  \"replace\": false,\n")
    item_tag_file.write("  \"values\":\n")
    item_tag_file.write("  [\n")
    item_tag_file.write("    \"#firmasupps:candle_holders\"\n")
    item_tag_file.write("  ]\n")
    item_tag_file.write("}\n")
    item_tag_file.close()


def write_sconces() :

    # supplementaries:sconces
    block_tag_file = open(supp_tag_path + "blocks\\sconces.json", "wt")
    block_tag_file.write("{\n")
    block_tag_file.write("  \"replace\": false,\n")
    block_tag_file.write("  \"values\":\n")
    block_tag_file.write("  [\n")

    block_tag_file.write("    \"firmasupps:sconce\",\n")
    block_tag_file.write("    \"firmasupps:sconce_soul\",\n")
    block_tag_file.write("    \"firmasupps:sconce_lever\"\n")

    block_tag_file.write("  ]\n")
    block_tag_file.write("}\n")
    block_tag_file.close()


    item_tag_file = open(supp_tag_path + "items\\sconces.json", "wt")
    item_tag_file.write("{\n")
    item_tag_file.write("  \"replace\": false,\n")
    item_tag_file.write("  \"values\":\n")
    item_tag_file.write("  [\n")

    item_tag_file.write("    \"firmasupps:sconce\",\n")
    item_tag_file.write("    \"firmasupps:sconce_soul\",\n")
    item_tag_file.write("    \"firmasupps:sconce_lever\"\n")

    item_tag_file.write("  ]\n")
    item_tag_file.write("}\n")
    item_tag_file.close()


    # minecraft:piglin_repellents
    block_tag_file = open(mc_tag_path + "blocks\\piglin_repellents.json", "wt")
    block_tag_file.write("{\n")
    block_tag_file.write("  \"replace\": false,\n")
    block_tag_file.write("  \"values\":\n")
    block_tag_file.write("  [\n")

    block_tag_file.write("    \"firmasupps:sconce_soul\"\n")

    block_tag_file.write("  ]\n")
    block_tag_file.write("}\n")
    block_tag_file.close()


    item_tag_file = open(mc_tag_path + "items\\piglin_repellents.json", "wt")
    item_tag_file.write("{\n")
    item_tag_file.write("  \"replace\": false,\n")
    item_tag_file.write("  \"values\":\n")
    item_tag_file.write("  [\n")

    item_tag_file.write("    \"firmasupps:sconce_soul\"\n")

    item_tag_file.write("  ]\n")
    item_tag_file.write("}\n")
    item_tag_file.close()






def write_all() :

    if not os.path.exists(mc_tag_path + "items\\") :

        os.makedirs(mc_tag_path + "items\\")

    if not os.path.exists(mc_tag_path + "blocks\\") :

        os.makedirs(mc_tag_path + "blocks\\")

    if not os.path.exists(supp_tag_path + "items\\") :

        os.makedirs(supp_tag_path + "items\\")

    if not os.path.exists(supp_tag_path + "blocks\\") :

        os.makedirs(supp_tag_path + "blocks\\")
        
    if not os.path.exists(firmasupps_tag_path + "items\\") :

        os.makedirs(firmasupps_tag_path + "items\\")

    if not os.path.exists(firmasupps_tag_path + "blocks\\") :

        os.makedirs(firmasupps_tag_path + "blocks\\")
    """
    if not os.path.exists(create_tag_path + "items\\") :

        os.makedirs(create_tag_path + "items\\")

    if not os.path.exists(create_tag_path + "blocks\\") :

        os.makedirs(create_tag_path + "blocks\\")
    """
    write_candle_holders()
    write_sconces()





if __name__ == "__main__" :

    write_all()
