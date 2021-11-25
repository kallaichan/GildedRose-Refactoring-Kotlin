package com.gildedrose

private const val AGED_BRIE = "Aged Brie"
private const val BACKSTAGE_PASS = "Backstage pass"
private const val SULFURAS = "Sulfuras"

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { item ->
            updateQualityOfIndividualItems(item)
        }
    }

    private fun updateQualityOfIndividualItems(item: Item) {
        if (item.isSulfuras()) {
            return
        }

        if (item.isAgedBrie()) {
            item.incrementQualityBy(2)
            decrementSellIn(item)
            return
        }

        if (item.isBackstagePass()) {
            item.incrementQualityBy(1)
            if (item.sellIn < 11) {
                item.incrementQualityBy(1)
            }
            if (item.sellIn < 6) {
                item.incrementQualityBy(1)
            }
            if (item.sellIn < 0) {
                item.quality = 0
            }
            return
        }

        decrementQuality(item)
        decrementSellIn(item)

        if (item.sellIn < 0) {
            decrementQuality(item)
        }
    }

    private fun decrementSellIn(item: Item) {
        item.sellIn--
    }

    private fun Item.incrementQualityBy(delta: Int) {
        quality += delta
        quality = quality.coerceAtMost(50)
    }

    private fun decrementQuality(item: Item) {
        if (item.quality > 0) {
            item.quality--
        }
    }

    private fun Item.isSulfuras() = name.contains(SULFURAS, ignoreCase = true)

    private fun Item.isBackstagePass() = name.contains(BACKSTAGE_PASS, ignoreCase = true)

    private fun Item.isAgedBrie() = name == AGED_BRIE

}

