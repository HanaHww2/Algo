<?php
  $str = trim(fgets(STDIN));
  $L = strlen($str);
  if ($L === 0) {
      echo 0;
      return;
  }

  $result = array_fill(0, $L, 0);
  $palidrom = array_fill(0, $L, false);

  $result[0] = 1;
  $palidrom[0] = true;
  
  for ($i = 1; $i < $L; $i++) {
    $min = $result[$i-1] + 1;
    $palidrom[$i] = true;

    for ($j = 0; $j < $i; $j++) {
      if (($str[$j] == $str[$i]) && ($j + 1 == $i || $palidrom[$j+1])) {
        $palidrom[$j] = true;
        $min = $j > 0 ? min($min, $result[$j-1] + 1) : min($min, 1);
      } else {
        $palidrom[$j] = false;
      }
    }
    $result[$i] = $min;
  }

  echo $result[$L-1];