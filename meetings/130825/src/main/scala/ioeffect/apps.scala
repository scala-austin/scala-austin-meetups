package org.atxscala.sideeffects
package ioeffect


object IoWithoutRt extends App {
  run("twice(effectVal)", twice(effectVal))
  run("twiceByName(effectVal)", twiceByName(effectVal))
  run("twice(effectDef)", twice(effectDef))
  run("twiceByName(effectDef)", twiceByName(effectDef))
}
