package org.atxscala.sideeffects
package ioeffect


object IoWithoutRt extends App {
  go("twice(effectVal)", twice(effectVal))
  go("twiceByName(effectVal)", twiceByName(effectVal))
  go("twice(effect)", twice(effect))
  go("twice(effectDef)", twice(effectDef))
  go("twiceByName(effect)", twiceByName(effect))
  go("twiceByName(effectDef)", twiceByName(effectDef))
}
