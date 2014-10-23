'use strict';

describe('eBankingApp.version module', function() {
  beforeEach(module('eBankingApp.version'));

  describe('version service', function() {
    it('should return current version', inject(function(version) {
      expect(version).toEqual('0.1');
    }));
  });
});
