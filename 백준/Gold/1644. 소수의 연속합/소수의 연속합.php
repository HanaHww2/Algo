<?php
 $num = trim(fgets(STDIN));

  if ($num < 2) {
      echo 0;
      return;
  }

 $prime = [];

 $isPrime = new SplFixedArray($num + 1);
 for ($i = 2; $i <= $num; $i++) $isPrime[$i] = true;
 $isPrime[0] = $isPrime[1] = false;

 function sieveOfEratosthenes(int $number) {

  global $isPrime, $prime;

  for ($i = 2; $i < $number + 1; $i++) {
    if ($isPrime[$i]) {
      $prime[$i] = 1;

      for ($j = $i * $i; $j < $number + 1; $j += $i) {
        $isPrime[$j] = false;
      }
    }
  }
 }

 sieveOfEratosthenes($num);

 $cnt = 0;
 $last = 0;
 $sum = [];
 foreach ($prime as $i => $val) {
  $nxt = $last + $i;
  $sum[$nxt] = 1;
  $last = $nxt;

  if ($nxt == $num) $cnt++;
  if ($nxt > $num) {
    $diff = $nxt - $num;
    if (isset($sum[$diff])) {
      $cnt++;
    }
  }
}

 echo $cnt;
