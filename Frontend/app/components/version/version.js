'use strict';

angular.module('eBankingApp.version', [
  'eBankingApp.version.interpolate-filter',
  'eBankingApp.version.version-directive'
])

.value('version', '0.1');
