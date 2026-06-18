"""
Mythic Upgrading Table GUI texture (256x256).

Layout (176x166 main area):
  Left panel (4-47, 9-76) — fuel, CENTERED:
    Slot 0 – necoium ingot : (16, 15)   centered in panel
    Fire animation          : (19, 36)  14x13
    Slot 1 – lava bucket   : (16, 53)   centered in panel

  Upgrade area (center):
    Slot 2 – netherite base : (53, 47)
    Slot 3 – gem ingot      : (71, 35)
    Slot 4 – crystal shard  : (71, 57)

  Arrow                     : (90, 47)  22x15
  Slot 5 – result           : (114, 42) 26x26   right edge = 140

  Armor stand preview       : texture frame (143,5)-(173,84)
                              entity center at pixel (156, 75)

  Necoium fuel bar          : centered in GUI, (58, 79) max 60x5
                              labeled row — just the bar frame here

  Player inventory          : (8, 84)  9x3
  Player hotbar             : (8, 142) 9x1

Widget sprites (x >= 176):
  Fire lit              : (176,  0) 14x13
  Bar filled (necoium)  : (176, 13) 60x5
  Arrow empty           : (176, 18) 22x15
  Arrow filled          : (176, 33) 22x15
"""

from PIL import Image
import os

BG      = (198, 198, 198, 255)
SLOT_BG = (139, 139, 139, 255)
DARK    = ( 85,  85,  85, 255)
DARKER  = ( 55,  55,  55, 255)
LIGHT   = (255, 255, 255, 255)
FIRE_BG = ( 50,  50,  50, 255)
NECOIUM = (200,  45, 148, 255)
FIRE_R  = (190,  35,   5, 255)
FIRE_O  = (220, 110,  20, 255)
FIRE_Y  = (240, 200,  20, 255)

img = Image.new('RGBA', (256, 256), (0, 0, 0, 0))

def px(x, y, c): img.putpixel((x, y), c)
def fill(x0,y0,x1,y1,c):
    for yy in range(y0, y1+1):
        for xx in range(x0, x1+1):
            img.putpixel((xx,yy),c)
def hline(x0,x1,y,c):
    for xx in range(x0,x1+1): img.putpixel((xx,y),c)
def vline(x,y0,y1,c):
    for yy in range(y0,y1+1): img.putpixel((x,yy),c)

def slot(x, y, w=18, h=18):
    fill(x,y,x+w-1,y+h-1, SLOT_BG)
    hline(x, x+w-1, y,     DARKER); vline(x, y, y+h-1,   DARKER)
    hline(x, x+w-1, y+h-1, LIGHT);  vline(x+w-1, y, y+h-1, LIGHT)
    hline(x+1,x+w-2,y+1,   DARKER); vline(x+1,y+1,y+h-2,  DARKER)
    hline(x+1,x+w-2,y+h-2, LIGHT);  vline(x+w-2,y+1,y+h-2, LIGHT)

def inset(x0,y0,x1,y1):
    hline(x0,x1,y0,DARKER); vline(x0,y0,y1,DARKER)
    hline(x0,x1,y1,LIGHT);  vline(x1,y0,y1,LIGHT)

# ── Background ─────────────────────────────────────────────────────────────
fill(0,0,175,165, BG)
hline(0,175,0, DARK);  vline(0,0,165, DARK)
hline(0,175,165,LIGHT); vline(175,0,165,LIGHT)
hline(1,174,1,DARK);   vline(1,1,164,DARK)

# ── Left fuel panel frame ──────────────────────────────────────────────────
inset(4, 9, 47, 76)

# Vertical separator
vline(51, 9, 76, DARKER)
vline(52, 9, 76, LIGHT)

# ── Fuel slots — centered in panel ────────────────────────────────────────
slot(16, 15)      # necoium ingot
# fire background
fill(19, 36, 32, 48, FIRE_BG)
inset(18, 35, 33, 49)
slot(16, 53)      # lava bucket

# ── Upgrade slots ─────────────────────────────────────────────────────────
slot(53, 47)      # netherite base
slot(71, 35)      # gem ingot
slot(71, 57)      # crystal shard

# ── Result slot (26x26) ───────────────────────────────────────────────────
slot(114, 42, 26, 26)

# ── Armor stand frame ─────────────────────────────────────────────────────
inset(143, 5, 173, 84)
fill(144, 6, 172, 83, (188, 188, 188, 255))   # slightly lighter bg

# ── Necoium fuel bar — centered in GUI ────────────────────────────────────
# Bar lives at (58,79)-(117,83), 60x5
inset(57, 78, 118, 84)
fill(58, 79, 117, 83, (80, 80, 80, 255))      # empty/dark bar background

# Small "flame" icon left of bar as context hint
fill(48, 79, 55, 83, FIRE_BG)
inset(47, 78, 56, 84)

# ── Player inventory ──────────────────────────────────────────────────────
for row in range(3):
    for col in range(9):
        slot(8 + col*18, 84 + row*18)

hline(7, 169, 139, DARKER)
hline(7, 169, 140, LIGHT)

for col in range(9):
    slot(8 + col*18, 142)

# ── Widgets (x >= 176) ────────────────────────────────────────────────────

# Fire sprite at (176,0) 14x13 — bright, readable
fire_rows = [
    "   YYYYYYY    ",
    "  YYYYYYYY    ",
    " OYYYYYYYYY   ",
    " OOYYYYYYYY   ",
    "OOOOYYYYYYY   ",
    "OOOOOYYYYY    ",
    "RROOOOOYYYY   ",
    "RRROOOOYYY    ",
    "RRRROOOYYY    ",
    " RRRROOOYY    ",
    "  RRRROOY     ",
    "   RRROO      ",
    "    RRR       ",
]
cmap = {'R':FIRE_R,'O':FIRE_O,'Y':FIRE_Y}
for fy, row_s in enumerate(fire_rows[:13]):
    for fx, ch in enumerate(row_s[:14]):
        if ch in cmap:
            img.putpixel((176+fx, fy), cmap[ch])

# Necoium bar filled at (176,13) 60x5
fill(176, 13, 235, 17, NECOIUM)
hline(176, 235, 13, (220, 80, 170, 255))  # highlight
hline(176, 235, 17, (140, 20, 100, 255))  # shadow

# Arrow empty (gray) at (176,18) 22x15
def draw_arrow(bx, by, col):
    fill(bx, by+6, bx+13, by+8, col)
    for dx in range(8):
        half = 7 - dx
        for dy in range(15):
            if abs(dy-7) <= half:
                img.putpixel((bx+14+dx, by+dy), col)

draw_arrow(176, 18, (85, 85, 85, 255))
draw_arrow(176, 33, (220, 220, 220, 255))

# ── Save ──────────────────────────────────────────────────────────────────
out_path = os.path.join(
    os.path.dirname(os.path.abspath(__file__)),
    "common","src","main","resources",
    "assets","mythicupgrades","textures","gui",
    "mythic_upgrading_table.png"
)
os.makedirs(os.path.dirname(out_path), exist_ok=True)
img.save(out_path)
print(f"Saved: {out_path}")
