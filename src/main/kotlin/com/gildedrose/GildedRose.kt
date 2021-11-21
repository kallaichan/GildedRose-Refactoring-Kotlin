package com.gildedrose

private const val AGED_BRIE = "Aged Brie"
private const val BACKSTAGE_PASS = "Backstage pass"
private const val SULFURAS = "Sulfuras"

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            if (!isAgedBrie(items[i]) && !isBackstagePass(items[i])) {
                if (items[i].quality > 0) {
                    if (!isSulfuras(items[i])) {
                        items[i].quality = items[i].quality - 1
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1

                    if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }
                    }
                }
            }

            if (!isSulfuras(items[i])) {
                items[i].sellIn = items[i].sellIn - 1
            }

            if (items[i].sellIn < 0) {
                if (!isAgedBrie(items[i])) {
                    if (!isBackstagePass(items[i])) {
                        if (items[i].quality > 0) {
                            if (!isSulfuras(items[i])) {
                                items[i].quality = items[i].quality - 1
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1
                    }
                }
            }
        }
    }

    private fun isSulfuras(item: Item) = item.name.contains(SULFURAS, ignoreCase = true)

    private fun isBackstagePass(item: Item) = item.name.contains(BACKSTAGE_PASS, ignoreCase = true)

    private fun isAgedBrie(item: Item) = item.name == AGED_BRIE

}

